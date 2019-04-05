package com.kone.authuser.service.hystrix;

import com.kone.authuser.service.AuthServiceClient;
import com.kone.authuser.utils.JWT;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceHystrix implements AuthServiceClient {
    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        return null;
    }
}
