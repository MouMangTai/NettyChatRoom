package com.moumangtai.demo.util;

import com.moumangtai.demo.constant.BaseConstant;
import com.moumangtai.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 */
public class JwtUtil {

    private static String secret = "a1g2y47dg3dj59fjhhsd7cnewy73j";

    /**
     * 生成token
     * @param user
     * @return
     */
    public static String getToken(User user) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("username", user.getUserName());
        claims.put("password", user.getPassWord());
        //生成token
        String token = Jwts.builder()
                .setClaims(claims)
                .setId(user.getId().toString())  //登录用户的id
                .setSubject(user.getUserName())  //登录用户的名称
                .setExpiration(new Date(System.currentTimeMillis() + BaseConstant.USER_TOKEN_MILL))//过期时间
                .setIssuedAt(new Date(System.currentTimeMillis()))//当前时间
                .signWith(SignatureAlgorithm.HS256, secret)//头部信息 第一个参数为加密方式为哈希512  第二个参数为加的盐为secret字符串
                .compact();
        return token;
    }

    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    public static Boolean judgeIsExpire(String token){
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            String id = claims.getId();
            System.out.println("id:"+id);
            System.out.println("过期时间:"+claims.getExpiration());
            return claims.getExpiration().getTime()<new Date().getTime();
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 根据token获取userId
     *
     * @param token
     * @return
     */
    public static String getUserIdByToken(String token){
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getId();
        } catch (Exception e) {
            return null;
        }
    }


}
