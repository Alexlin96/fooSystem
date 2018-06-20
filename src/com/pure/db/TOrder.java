package com.pure.db;

import java.util.Date;

public class TOrder {
    private Integer id;

    private Integer userid;

    private Integer screeningsid;

    private Date buytime;

    private String position;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getScreeningsid() {
        return screeningsid;
    }

    public void setScreeningsid(Integer screeningsid) {
        this.screeningsid = screeningsid;
    }

    public Date getBuytime() {
        return buytime;
    }

    public void setBuytime(Date buytime) {
        this.buytime = buytime;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}