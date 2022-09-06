package com.example.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.entity.Account;

import java.util.Calendar;

/**
 * @author admin
 */
public class JWTUtils {

    static String SECRET_KEY = "jkasehfjqewhgkjfeshfrwiehfi";

    /**
     * 获取token
     * @return token
     */
    public static String getToken(String id) {
        Calendar instance = Calendar.getInstance();
        //默认令牌过期时间7天
        instance.add(Calendar.DATE, 7);

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("id", id);

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    /**
     * 验证token合法性 成功返回token
     */
    public static boolean verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); //算法
            JWTVerifier verifier = JWT.require(algorithm)

                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public static String getId(String token){
        try{
            return JWT.decode(token).getClaim("id").asString();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "";
    }
}

