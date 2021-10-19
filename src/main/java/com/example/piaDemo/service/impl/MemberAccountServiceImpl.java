package com.example.piaDemo.service.impl;

import com.example.piaDemo.ErrorEnum;
import com.example.piaDemo.dao.mapper.MemberAccountMapper;
import com.example.piaDemo.model.MemberAccount;
import com.example.piaDemo.service.MemberAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MemberAccountServiceImpl implements MemberAccountService {

    @Autowired
    private MemberAccountMapper memberAccountMapper;

    // 回傳格式
    private Map<String, Object> getRetDataTemplate() {
        Map<String, Object> retData = new HashMap<String, Object>();
        retData.put("Code", ErrorEnum.NO_ERROR.getCode());
        retData.put("Msg", ErrorEnum.NO_ERROR.getDescription());
        retData.put("Data", null);

        return retData;
    }

    // 產生 salt 值
    private String generateSalt() {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }

    // 對password + salt 進行三重加密
    private String getMd5Password(String password, String salt) {
        String str = password + salt;
        for (int i = 0; i < 3; i++) {
            str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
        }
        return str;
    }

    @Transactional
    public Map<String, Object> register(MemberAccount memberAccount) {
        Map<String, Object> retData = getRetDataTemplate();

        // 檢查需要傳入的值，是否都有帶入
        if (memberAccount.getAccount() == null || memberAccount.getPassword() == null || memberAccount.getName() == null || memberAccount.getPhone() == null) {
            retData.put("Code", ErrorEnum.MISSING_PARAMS.getCode());
            retData.put("Msg", ErrorEnum.MISSING_PARAMS.getDescription());
            return retData;
        }

        // 檢查手機是否符合10碼
        if (!memberAccount.getPhone().matches("^[0-9]{10}$")) {
            retData.put("Code", ErrorEnum.PHONE_ERROR.getCode());
            retData.put("Msg", ErrorEnum.PHONE_ERROR.getDescription());
            return retData;
        }

        // 檢查帳號是否已經存在
        MemberAccount data = memberAccountMapper.getMemberAccountByAccount(memberAccount.getAccount());
        if (data != null) {
            retData.put("Code", ErrorEnum.DUPLICATE_MEMBER.getCode());
            retData.put("Msg", ErrorEnum.DUPLICATE_MEMBER.getDescription());
            return retData;
        }

        // 產生 salt 和 password
        String salt = generateSalt();
        memberAccount.setSalt(salt);
        String md5Password = getMd5Password(memberAccount.getPassword(), salt);
        memberAccount.setPassword(md5Password);

        // 去資料庫新增帳號
        Integer result = memberAccountMapper.add(memberAccount);
        if (result == 0) {
            retData.put("Code", ErrorEnum.DB_ERROR.getCode());
            retData.put("Msg", ErrorEnum.DB_ERROR.getDescription());
            return retData;
        }
        return retData;
    }

    @Transactional
    public Map<String, Object> login(MemberAccount memberAccount) {
        Map<String, Object> retData = getRetDataTemplate();

        // 檢查需要傳入的值，是否都有帶入
        if (memberAccount.getAccount() == null || memberAccount.getPassword() == null) {
            retData.put("Code", ErrorEnum.MISSING_PARAMS.getCode());
            retData.put("Msg", ErrorEnum.MISSING_PARAMS.getDescription());
            return retData;
        }

        // 去資料庫查詢
        MemberAccount data = memberAccountMapper.getMemberAccountByAccount(memberAccount.getAccount());
        if (data == null) {
            retData.put("Code", ErrorEnum.NO_MEMBER.getCode());
            retData.put("Msg", ErrorEnum.NO_MEMBER.getDescription());
            return retData;
        }

        // 比對密碼
        String md5Password = getMd5Password(memberAccount.getPassword(), data.getSalt());
        if (!data.getPassword().equals(md5Password)) {
            retData.put("Code", ErrorEnum.PASSWORD_ERROR.getCode());
            retData.put("Msg", ErrorEnum.PASSWORD_ERROR.getDescription());
            return retData;
        }

        retData.put("Data", data.generateReturnMap());
        return retData;
    }
}
