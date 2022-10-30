package com.simpleShop.mySimple.auth;

import java.util.Date;

public class JwtService {

    public String createJwt(int userNum){
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type","jwt")
                .claim("userNum",userNum)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis()+1*(1000*60*60*24*365))) //발급날짜 계산
                .signWith(SignatureAlgorithm.HS256, Secret.JWT_SECRET_KEY) //signature 부분
                .compact();
    }
}
