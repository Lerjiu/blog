package com.blog.utils;

import com.blog.exception.BusinessException;
import com.blog.exception.Code;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

public class Token {
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;
    private static final String KEY = "blog_authorization_token";

    public static String createToken(int id) {
        HashMap<String, Object> header = new HashMap<>();
        header.put("typ","JWT");
        header.put("alg","HS256");
        JwtBuilder jwtBuilder = Jwts.builder().setHeader(header)
                .setSubject(String.valueOf(id))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(getKey(), SignatureAlgorithm.HS256);
        return jwtBuilder.compact();
    }

    public static void verifyToken(String token, String id) {
        String token_id = null;
        try {
            token_id = Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            throw BusinessException.exception(Code.TOKEN_EXPIRED_TIME, Code.TOKEN_EXPIRED_TIME_MESSAGE, e);
        }
        if (!id.equals(token_id)) {
            throw BusinessException.exception(Code.TOKEN_INVALID_USER, Code.TOKEN_INVALID_USER_MESSAGE);
        }
    }

    private static Key getKey() {
        return Keys.hmacShaKeyFor(KEY.getBytes());
    }
}
