package com.example.demo;

import com.example.entity.Account;
import com.example.entity.Tips;
import com.example.entity.User;
import com.example.mapper.AccountMapper;
import com.example.mapper.UserMapper;
import com.example.util.JWTUtils;
import com.example.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

@RestController
public class BookController {

    static String from = "desyang@qq.com";
    @RequestMapping("/hello")
    public String hello() {
        File file = new File("D:\\Java\\demo\\src\\main\\resources\\static\\time.htm");

        StringBuffer str = new StringBuffer();
        try {
            FileInputStream in = new FileInputStream(file);
            int n = -1;
            byte[] b = new byte[1024];
            while((n = in.read(b, 0, b.length)) != -1){
                str.append(new String(b));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str.toString();
    }

    @RequestMapping(value = "/index/{id}",method = RequestMethod.GET)
    public User index(@PathVariable int id) {
        System.out.println("SpringBoot is running...");
        UserMapper mapper = MapperUtil.getUserMapper(true);
        User user = mapper.selectUserById(id);
        return user;
    }

    @RequestMapping(value = "users/{name}/{age}", method = RequestMethod.GET)
    public int saveUser(@PathVariable String name, @PathVariable int age, @RequestParam String str) {
            UserMapper mapper = MapperUtil.getUserMapper(true);
            User user = new User();
            user.setAge(age);
            user.setName(name);
            System.out.println(user);
            System.out.println("数据插入成功");
            System.out.println(str);
            return mapper.insertUser(user);
    }

    @RequestMapping(value = "/delete")
    public int deleteUser(Integer id) {

        UserMapper mapper = MapperUtil.getUserMapper(true);
        int status = mapper.deleteUser(id);
        if(status == 1) {
            System.out.println("delete " + id + " successfully");
        }
        else {
            System.out.println("fail");
        }
        return status;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public int insertUser(@RequestParam String name, @RequestParam int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);

        UserMapper mapper = MapperUtil.getUserMapper(true);
        System.out.printf("func insertUser(%s, %d) was call...\n", name, age);
        return mapper.insertUser(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User selectUserById(@RequestParam int id) {
        User user = MapperUtil.getUserMapper(true)
                .selectUserById(id);
        System.out.printf("func selectUser(%d) was call...\n", id);
        return user;
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public int update(@RequestBody User user) {
        UserMapper mapper = MapperUtil.getUserMapper(true);
        System.out.println("update");
        System.out.println(user);
        return mapper.updateUser(user);
    }

    @Autowired
//    Test mail;
    MyMailService mail;
    @RequestMapping(value = "/login/{to}", method = RequestMethod.GET) // 邮箱获取验证码
    public Tips send(@PathVariable String to) {
        try {
            System.out.println("发送邮件");
            Random random = new Random();
            String text = "尊敬的:" + to + "用户您好！\n" + "您的验证码:";
            long yzm = random.nextInt(100000, 999999);
            text += " " + yzm;
            mail.sendMail(from, to,  "标题：官方验证码", text);
            DemoApplication.hashMap.put(to, "" + yzm);
            DemoApplication.queue.add(new Pair(System.currentTimeMillis(), to));
        } catch (Exception e) {
            return new Tips(0, "发生错误, 请检查邮箱是否正确！");
        }
        return new Tips(1, "发送成功");
    }

    @RequestMapping(value = "/login/{yzm}", method = RequestMethod.PUT) // 验证码检测
    public Tips test(@RequestBody Account acc, @PathVariable String yzm) {
        if(DemoApplication.hashMap.containsKey(acc.getEmail()) && DemoApplication.hashMap.get(acc.getEmail()).equals(yzm)) {

            AccountMapper mapper = MapperUtil.getAccountMapper(true);
            try{

                Random random = new Random();
                int password = random.nextInt(100000, 999999);
                acc.setCypher(Integer.toString(password)); // 默认密码
                mapper.insertAccount(acc);
            } catch (Exception e) {
                return new Tips(0, "邮箱已被注册");
            }

            String account = mapper.selectAccountByEmail(acc.getEmail());
            MapperUtil.getUserInfoMapper(true)
                    .insertId(Integer.parseInt(account));
            return new Tips(1, account);
        }

        return new Tips(0, "验证码错误");
    }

    @PutMapping(value = "/login") // 设置初始密码
    public Tips register(@RequestBody Account acc) {
        if(acc.getCypher().length() < 6) return new Tips(0, "密码长度必须大于6");
        MapperUtil.getAccountMapper(true)
                .updateAccount(acc);
        return new Tips(1, "密码设置成功");
    }



    @RequestMapping(value = "/login", method = RequestMethod.POST) // 登陆账号
    public Tips login(@RequestBody Account acc) {
        String status = "";
        status = MapperUtil.getAccountMapper(true)
                .login(acc);
        if(!status.isEmpty()) {
            return new Tips(1, JWTUtils.getToken(status));
        }
        return new Tips(0, "账号或密码错误");
    }

    @RequestMapping(value = "/forget/{yzm}", method = RequestMethod.PUT)
    public Tips resetPassword(@PathVariable String yzm, @RequestBody Account acc) {
            if(acc.getCypher().length() < 6) return new Tips(0, "密码长度必须大于6");
        if(DemoApplication.hashMap.containsKey(acc.getEmail()) && DemoApplication.hashMap.get(acc.getEmail()).equals(yzm)) {

            MapperUtil.getAccountMapper(true)
                    .updateAccount(acc);
            return new Tips(1, "密码重置成功");
        }

        return new Tips(0, "验证码错误");
    }


}