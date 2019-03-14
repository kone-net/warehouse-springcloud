package com.kone.userservice.service;

import com.kone.utils.entity.User;
import com.kone.utils.msg.MsgEnum;
import com.kone.utils.msg.ResponseMsg;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userService")
public class UserService {
    @Resource
    private com.kone.commonsDao.dao.UserMapper userMapper;

    @RequestMapping("/login")
    @ResponseBody
    public ResponseMsg login(@RequestBody User user) {
        ResponseMsg<User> msg = new ResponseMsg<User>();
        if(null == user) {
            msg.setCode(MsgEnum.NULL_INFO.getCode());
            msg.setMsg(MsgEnum.NULL_INFO.getMsg());
            return msg;
        }

        User user1 = userMapper.selectByUsername(user.getUsername());
        if(null == user1) {
            msg.setCode(MsgEnum.USERNAME_ERROR.getCode());
            msg.setMsg(MsgEnum.USERNAME_ERROR.getMsg());
            return msg;
        }
        if(null == user.getPassword() || !user.getPassword().equals(user1.getPassword())) {
            msg.setCode(MsgEnum.PASSWORD_ERROR.getCode());
            msg.setMsg(MsgEnum.PASSWORD_ERROR.getMsg());
            return msg;
        }
        msg.setData(user1);
        return msg;
    }

    @GetMapping("getOk")
    public String getOk() {
        return "ok";
    }
}
