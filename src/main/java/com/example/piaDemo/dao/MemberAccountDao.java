package com.example.piaDemo.dao;

import com.example.piaDemo.model.MemberAccount;

public interface MemberAccountDao {
    public Integer add(MemberAccount memberAccount);

    public MemberAccount getMemberAccountByAccount(String account);

    public MemberAccount checkLogin(String account, String password);
}
