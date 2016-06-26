package com.example.zzt.whyfi.model.db;

/**
 * Created by zzt on 6/26/16.
 * <p/>
 * Usage:
 */
public class Msg {

    private String did;
    private String content;
    private String time;
    private boolean self;


    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
