package com.example.piaDemo.controller;


import com.example.piaDemo.model.MemberAccount;
import com.example.piaDemo.service.MemberAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class MemberAccountController {

    @Autowired
    private MemberAccountService memberAccountService;

    //  登入
    @GetMapping("/member/login")
    public Map<String, Object> login(@ModelAttribute MemberAccount memberAccount) {
        return memberAccountService.login(memberAccount);
    }

    //  註冊
    @PostMapping("/member/register")
    public Map<String, Object> register(@ModelAttribute MemberAccount memberAccount) {
        return memberAccountService.register(memberAccount);
    }
}
