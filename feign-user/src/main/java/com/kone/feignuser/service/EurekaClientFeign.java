package com.kone.feignuser.service;

import com.kone.utils.entity.User;
import com.kone.utils.msg.ResponseMsg;
import com.kone.feignuser.FeignConfig.FeignConfig;
import com.kone.feignuser.FeignConfig.FeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", fallback = FeignFallback.class, configuration = FeignConfig.class)
public interface EurekaClientFeign {

    @RequestMapping(value = "/userService/login", consumes = "application/json")
    @ResponseBody
    ResponseMsg<User> login(@RequestBody User user);

    @GetMapping("/userService/getOk")
    String getOk();
}
