package com.cmcc.hsp.netty.model;

import java.util.Date;

public class LoginUser {

    private String userName;
    private String password;
    private Date longinDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLonginDate() {
        return longinDate;
    }

    public void setLonginDate(Date longinDate) {
        this.longinDate = longinDate;
    }
}
