package com.pure.db;

import java.util.Date;

public class TScreenings {
    private Integer id;

    private Integer filmid;

    private String type;

    private String price;

    private String remark;

    private Date playTime;

    private Integer auditiorid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFilmid() {
        return filmid;
    }

    public void setFilmid(Integer filmid) {
        this.filmid = filmid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Date playTime) {
        this.playTime = playTime;
    }

    public Integer getAuditiorid() {
        return auditiorid;
    }

    public void setAuditiorid(Integer auditiorid) {
        this.auditiorid = auditiorid;
    }
}