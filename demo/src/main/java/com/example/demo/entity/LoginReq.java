package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class LoginReq implements Serializable {

    private String userName;
    private String pwd;

    /**
     * DateTimeFormat:
     * 规定客户端调用的时候传递的格式只能是 2022-12-06 这种，比如时间戳 1670256000000 就不行
     * JSON parse error: Cannot deserialize value of type `java.util.Date` from String "1670256000000": expected format "yyyy-MM-dd"
     *
     * JsonFormat : 比如后端 修改 birthDay 的值为 new Date()，那么返回给前端的对应的是 2023-01-03
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
