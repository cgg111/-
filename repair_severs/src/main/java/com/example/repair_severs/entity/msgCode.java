package com.example.repair_severs.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class msgCode implements Serializable {
    private int code; //状态码
    private String msg;//错误消息
    private Object obj;//返回对象
    private String tokens;//登录凭证

    public static msgCode error(int i, String ore) {
        msgCode m = new msgCode();
        m.setMsg(ore);
        return m;
    }

    public static msgCode success() {
        msgCode m = new msgCode();
        m.setMsg("success");
        return m;
    }
}
