package com.ltf.azerothcore.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.security.NoSuchAlgorithmException;

public interface IUserService {
    int regedit(String username, String password) throws Exception;

    String changePassword(String username, String newPassword, String currentPassword) throws Exception;
}
