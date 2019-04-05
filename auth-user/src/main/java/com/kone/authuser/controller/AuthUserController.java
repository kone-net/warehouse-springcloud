package com.kone.authuser.controller;

import com.kone.authuser.service.AuthServiceClient;
import com.kone.authuser.utils.BPwdEncoderUtil;
import com.kone.authuser.utils.JWT;
import com.kone.authuser.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/user")
public class AuthUserController {

    @PostMapping("/register")
    public String postUser(@RequestParam("username") String username,
                         @RequestParam("password") String password){

        return username + " " + BPwdEncoderUtil.BCryptPassword(password);
    }

    @Resource
    private AuthServiceClient client;
    @PostMapping("/login")
    public JWT login(@RequestParam("username") String username,
                           @RequestParam("password") String password){
        System.out.println("username is " + username + " " + password);
    // 从auth-service获取JWT
        JWT jwt = client.getToken("Basic Y2xpZW50OmNsaWVudA==", "password", username, password);
        if(jwt == null){
            System.out.println("jwt is null");
        }

        return jwt;
    }

    @GetMapping("/getName")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getName(){

        return "get the name";
    }

    @GetMapping("/getNameNo")
    public Authentication getNameNo(HttpServletRequest request, Principal principal, Authentication authentication){
        String header = request.getHeader("Authorization");
        System.out.println(header);
//        JWTUtils.parseJWT(header);

        return authentication;
    }

}
