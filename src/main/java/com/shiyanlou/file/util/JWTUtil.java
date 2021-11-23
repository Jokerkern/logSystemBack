package com.shiyanlou.file.util;

import java.util.Date;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {
    public static final String JWT_ID = UUID.randomUUID().toString();
    public static final String JWT_SECRET = "c2hpeWFubG91MjAyMTAzMDc=";
    public static final int EXPIRE_TIME = 60 * 60 * 1000 * 24 * 7; // 过期时间一个星期

    public static SecretKey generalSecretKey() {
        String secret = JWT_SECRET;
        // 本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(JWT_SECRET);
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }    

    /**
    *
    * @param issuer jwt签发者
    * @param audience jwt接收者
    * @param subject 用户信息，可以将多个关键信息转为JSON进行存放
    * @return JWT字符串
    * @throws Exception
    */
    public static String createJWT(String issuer, String audience, String subject) throws Exception {
        //设置签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Map<String, Object> claims = new HashMap<>(2);
        claims.put("username", "admin");
        claims.put("password", "010203");

        long nowTime = System.currentTimeMillis();
        Date issuedAt = new Date(nowTime);
        SecretKey key = generalSecretKey();
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(JWT_ID)
                .setIssuedAt(issuedAt)
                .setIssuer(issuer)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        // 设置过期时间
        long expTime = EXPIRE_TIME;
        if (expTime >= 0) {
            long exp = nowTime + expTime;
            builder.setExpiration(new Date(exp));
        }
        builder.setAudience(audience);
        return builder.compact();
    }    

    /**
    * 解析JWT
    * @param jwt jwt串
    * @return
    * @throws Exception
    */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey key = generalSecretKey(); // 签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser() // 得到DefaultJwtParser
                .setSigningKey(key) // 设置签名的秘钥
                .parseClaimsJws(jwt).getBody(); // 设置需要解析的jwt
        return claims;
    }    
}