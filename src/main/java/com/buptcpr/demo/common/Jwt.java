package com.buptcpr.demo.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;


public class Jwt {
    public static SecretKey generalKey(){
        byte[] encodedKey = Base64.decodeBase64("Ef5OZAMPaXnLlSNL");//自定义
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    public static String createJWT(
                                   long TTLMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        // 添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("message", "你解你🐎呢")
                .setIssuer("BUPTSSE")//发行者，自定义
                .setAudience("client")
                .signWith(signatureAlgorithm, generalKey());
        // 添加Token过期时间
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        // 生成JWT
        return builder.compact();
    }

    public static Claims parseJWT(String jsonWebToken) {
        try {
            Claims claims = Jwts.parser().setSigningKey(generalKey())
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
}
