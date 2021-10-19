package com.example.piaDemo.model;

import com.example.piaDemo.ErrorEnum;

import java.util.HashMap;
import java.util.Map;

public class MemberAccount {

    private Integer id;
    private String account;
    private String password;
    private String salt;
    private String create_time;
    private String update_time;
    private String name;
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString() {
        return "MemberAccount(id=" + this.id + ", account=" + this.account + ", name=" + this.name + ", phone=" + this.phone + ")";
    }

    public Map<String, Object> generateReturnMap() {
        Map<String, Object> retData = new HashMap<String, Object>();
        retData.put("id", this.id);
        retData.put("account", this.account);
        retData.put("name", this.name);
        retData.put("phone", this.phone);
        retData.put("create_time", this.create_time);
        retData.put("update_time", this.update_time);
        return retData;
    }
}
