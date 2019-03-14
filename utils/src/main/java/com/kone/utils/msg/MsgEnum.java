package com.kone.utils.msg;

public enum MsgEnum {
    SERVER_ERROR("服务器内部错误", 1000),
    NULL_INFO("无效信息", 1001),
    USERNAME_ERROR("用户名错误", 1002),
    PASSWORD_ERROR("密码错误", 1003),
    SAVE_ERROR("保存失败", 1004),
    NEED_INPUT_FIELD_ERROR("请输入必填项", 1005),
    ALREADY_EXIST("已经存在", 1006),
    INPUT_THE_RIGHT_NUMBER("请输入正确的数字",1007);
    private String msg;

    private Integer code;

    MsgEnum(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
