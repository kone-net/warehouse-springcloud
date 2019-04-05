package com.kone.authuser.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

public class JWTUtils {

    public static void parseJWT(String auth) {
        if(null != auth) {
            String token = StringUtils.substringAfter(auth,"Bearer ");

            System.out.println(token);

            Resource resource = new ClassPathResource("public.cert");
            String publicKey;
            try {
                publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Claims body = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token).getBody();

            String username = (String) body.get("username");
            System.out.println(username);
        }
    }
}
