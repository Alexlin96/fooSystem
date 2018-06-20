package com.pure.db;

import java.util.Date;

public class TReply {
    private Integer id;

    private Integer criticid;

    private String content;

    private Date createdate;

    private Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCriticid() {
        return criticid;
    }

    public void setCriticid(Integer criticid) {
        this.criticid = criticid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}