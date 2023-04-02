package com.ltf.azerothcore.mapper;

import com.ltf.azerothcore.entity.DbUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 持久层
 */
@Mapper
public interface DbUserMapper {
    int regedit(DbUser dbUser);

    DbUser selectDbUSerByUsername(String username);

    void upDateVerifier(@Param("username") String username,@Param("newVerifier") byte[] newVerifier);
}
