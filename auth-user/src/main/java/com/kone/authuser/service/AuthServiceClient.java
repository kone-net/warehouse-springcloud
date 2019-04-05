package com.kone.authuser.service;

import com.kone.authuser.service.hystrix.AuthServiceHystrix;
import com.kone.authuser.utils.JWT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//@FeignClient(value = "auth-service", fallback = AuthServiceHystrix.class)
@FeignClient(value = "auth-service")
public interface AuthServiceClient {
    @RequestMapping(value = "/uaa/oauth/token", consumes = "application/json", method = RequestMethod.POST)
    @ResponseBody
    JWT getToken(@RequestHeader("Authorization") String authorization,
                 @RequestParam("grant_type") String type,
                 @RequestParam("username") String username,
                 @RequestParam("password") String password);

}
