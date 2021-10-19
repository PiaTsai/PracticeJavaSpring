package com.example.piaDemo.service;

import com.example.piaDemo.model.MemberAccount;

import java.util.Map;

public interface MemberAccountService {

    // 登入與註冊
    Map<String, Object> register(MemberAccount memberAccount);

    Map<String, Object> login(MemberAccount memberAccount);
}
