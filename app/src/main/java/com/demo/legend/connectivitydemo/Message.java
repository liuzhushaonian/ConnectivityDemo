package com.demo.legend.connectivitydemo;

/**
 *
 * Created by legend on 2018/3/21.
 */

public class Message {

    private String msg;
    private int type;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Message(String msg, int type) {

        this.msg = msg;
        this.type = type;
    }
}
