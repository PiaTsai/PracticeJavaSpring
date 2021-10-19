package com.example.piaDemo;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import com.example.piaDemo.dao.mapper.MemberAccountMapper;
import com.example.piaDemo.model.MemberAccount;

@SpringBootTest
public class MemberAccountTest {

    @Autowired
    private MemberAccountMapper memberAccountMapper;

    private String getMd5Password(String password, String salt) {
        // 對password + salt 進行三重加密
        String str = password + salt;
        for (int i = 0; i < 3; i++) {
            str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
        }
        return str;
    }

    @Test
    void testAdd() {

        String account = "admin2";
        String password = "test1234";
        String salt = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
        String md5Password = getMd5Password(password, salt);

        MemberAccount memberAccount = new MemberAccount();
        memberAccount.setAccount(account);
        memberAccount.setPassword(md5Password);
        memberAccount.setSalt(salt);

        Integer id = memberAccountMapper.add(memberAccount);
        System.err.println(id);
    }

    @Test
    void testGetMemberAccountByAccount() {

        String account = "admin3";

        MemberAccount data = memberAccountMapper.getMemberAccountByAccount(account);
        System.err.println(data);
    }

    @Test
    void testEnum() {
        System.out.println(ErrorEnum.DUPLICATE_MEMBER.getCode());
        System.out.println(ErrorEnum.DUPLICATE_MEMBER.getDescription());
    }
}
