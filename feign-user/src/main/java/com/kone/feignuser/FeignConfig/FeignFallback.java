package com.kone.feignuser.FeignConfig;

import com.kone.utils.entity.User;
import com.kone.utils.msg.MsgEnum;
import com.kone.utils.msg.ResponseMsg;
import com.kone.feignuser.service.EurekaClientFeign;
import org.springframework.stereotype.Component;

@Component
public class FeignFallback implements EurekaClientFeign {

    @Override
    public ResponseMsg login(User user) {
        ResponseMsg<User> msg = new ResponseMsg<User>(MsgEnum.SERVER_ERROR.getCode(), MsgEnum.SERVER_ERROR.getMsg());
        return msg;
    }

    @Override
    public String getOk() {
        return null;
    }
}
