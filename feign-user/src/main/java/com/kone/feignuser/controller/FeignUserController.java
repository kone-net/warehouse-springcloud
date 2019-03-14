package com.kone.feignuser.controller;

import com.kone.utils.entity.User;
import com.kone.utils.msg.ResponseMsg;
import com.kone.feignuser.service.EurekaClientFeign;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/feignUser")
public class FeignUserController {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Value("${server.port}")
    private String port;

    @Resource
    private EurekaClientFeign eurekaClientFeign;

    @PostMapping("/login")
    public String login(User user, Model model) {
        logger.info("user " + user.getUsername() + " is login!");
        ResponseMsg<User> msg = eurekaClientFeign.login(user);
        if(msg.getData() == null) {
            logger.info("null");
        } else {
            User user1 = msg.getData();
            logger.info("not null" + user1.getUsername());
        }
        logger.info(msg.getMsg());
        if(msg.getCode() == 200) {
            model.addAttribute("user", user);
            return "index";
        }
        return "login";
    }

    @GetMapping("/go")
    public String go() {
        return "login";
    }

}
