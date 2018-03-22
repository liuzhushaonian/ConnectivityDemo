package com.demo.legend.connectivitydemo;

/**
 *
 * Created by legend on 2018/3/21.
 */

public class Msg {

    private byte[] bytes;
    private int count;


    public Msg(byte[] bytes, int count) {
        this.bytes = bytes;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
