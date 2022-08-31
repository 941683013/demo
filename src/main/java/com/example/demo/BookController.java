package com.example.demo;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
public class BookController {

    @ResponseBody
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

    @ResponseBody
    @RequestMapping(value = "/index/{id}",method = RequestMethod.GET)
    public User index(@PathVariable int id) {
        System.out.println("SpringBoot is running...");
        SqlSession session = MybatisUtil.getSqlSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.selectUserById(id);
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "users/{name}/{age}", method = RequestMethod.GET)
    public User saveUser(@PathVariable String name, @PathVariable int age, @RequestParam String str) {
            SqlSession session = MybatisUtil.getSqlSession(true);
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = new User();
            user.setAge(age);
            user.setName(name);
            System.out.println(user);
            System.out.println("数据插入成功");
            System.out.println(str);
            return user;
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public int deleteUser(Integer id) {

        SqlSession session = MybatisUtil.getSqlSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class);
        int status = mapper.deleteUser(id);
        if(status == 1) {
            System.out.println("delete " + id + " successfully");
        }
        else {
            System.out.println("fail");
        }
        return status;
    }

    @ResponseBody
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public int insertUser(@RequestParam String name, @RequestParam int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);

        SqlSession session = MybatisUtil.getSqlSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class);
        System.out.printf("func insertUser(%s, %d) was call...", name, age);
        return mapper.insertUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User selectUserById(@RequestParam int id) {
        User user = MybatisUtil.getSqlSession(true)
                .getMapper(UserMapper.class)
                .selectUserById(id);
        System.out.printf("func selectUser(%d) was call...", id);
        return user;
    }


}