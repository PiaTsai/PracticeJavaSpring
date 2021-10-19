package com.example.piaDemo.dao.mapper;

import com.example.piaDemo.model.MemberAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberAccountMapper {

    String tableName = "test.member_account";

    @Insert(" INSERT INTO  " + tableName + "( "
            + "		ACCOUNT, PASSWORD, SALT, "
            + "     NAME, PHONE,"
            + "		CREATE_TIME, UPDATE_TIME "
            + " ) "
            + " VALUE ( "
            + "		#{account}, #{password}, #{salt}, "
            + "     #{name}, #{phone}, "
            + "		NOW(), NOW() "
            + " ) ")
    Integer add(MemberAccount memberAccount);

    @Select(" SELECT "
            + "	* "
            + " FROM "
            + tableName
            + " WHERE "
            + "	ACCOUNT = #{account}")
    MemberAccount getMemberAccountByAccount(String account);
}
