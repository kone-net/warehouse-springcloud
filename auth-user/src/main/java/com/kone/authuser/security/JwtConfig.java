package com.kone.authuser.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

@Configuration
public class JwtConfig {
    @javax.annotation.Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean
    @Qualifier("tokenStore")
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        // 用作JWT转换器
        JwtAccessTokenConverter converter =  new JwtAccessTokenConverter();
        Resource resource = new ClassPathResource("public.cert");
        String publicKey;
        try {
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //设置公钥
        converter.setVerifierKey(publicKey);

        return converter;
    }

    @Test
    public void test() {
        JwtAccessTokenConverter converter =  new JwtAccessTokenConverter();
        Resource resource = new ClassPathResource("public.cert");
        String publicKey;
        try {
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
//
            String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJrb25lMSIsInNjb3BlIjpbInNlcnZpY2UiXSwiZXh0X25hbWUiOiJLb25lX25hbWUiLCJleHAiOjE1NTY5NTY1OTksImNsaWVudF9uYW1lIjoia29uZTEiLCJhdXRob3JpdGllcyI6WyJBRE1JTiJdLCJqdGkiOiJkOTEzYzRjNy1hZDkwLTRiNmUtODNkOC1hMWY5NzRiNWUzYzgiLCJjbGllbnRfaWQiOiJjbGllbnQiLCJ1c2VybmFtZSI6eyJpZCI6MiwidXNlcm5hbWUiOiJrb25lMSIsInBhc3N3b3JkIjoia29uZTEiLCJhdXRob3JpdGllcyI6W10sImVuYWJsZWQiOnRydWUsImFjY291bnROb25FeHBpcmVkIjp0cnVlLCJhY2NvdW50Tm9uTG9ja2VkIjp0cnVlLCJjcmVkZW50aWFsc05vbkV4cGlyZWQiOnRydWV9fQ.KIijrJaeT6BDuSEtlVfPrd6JXGlgRSVseWriZY0LMbsYhhDdAke5Vd1yl6EJLGJe0T55kM4soM4fpdP-2DkgX1boPHR23HthKas2NEddXNkZLALsHXSopgO7bP38cmnIGU-6FSBKqbF-f1SVVWLcc-15ZgSpZepp4Nmq0P-SGEDJTppxz8aEJ5EA0iAcxlaQCxdd4bwi1uhpJhZH1C34qMr1o3V62Bpp_xzRfExo-pZjWWaSZSgao1tZcmHx6-qIT0-AUxP7-hPGfXRzCR9wJ6hfn8y_6v_3VmZVc7O-Otz3u45L4BKLml_JhVJLHMKodq5MA9cHhmWUBiOQPGVtvw";
//            byte[] bytes = Base64.encodeBase64("kone".getBytes("utf-8"));
//            Claims body = Jwts.parser().setSigningKey(bytes)
//                    .parseClaimsJws(token).getBody();
//
//            String username = (String) body.get("username");
//            System.out.println(username);

            Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publicKey));

            System.out.println(jwt.getClaims());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
