package com.ltf.azerothcore.service.impl;

import com.ltf.azerothcore.entity.DbUser;
import com.ltf.azerothcore.mapper.DbUserMapper;
import com.ltf.azerothcore.service.IUserService;
import com.ltf.azerothcore.utils.RandomBytes;
import com.ltf.azerothcore.utils.VerifierCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private DbUserMapper dbUserMapper;

    /**
     * 创建账户
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public int regedit(String username, String password) throws Exception {
        byte[] salt = RandomBytes.random32Bytes();
        DbUser dbUser = new DbUser();
        dbUser.setUsername(username);
        dbUser.setSalt(salt);
        dbUser.setVerifier(VerifierCalculator.calculateVerifier(username,password,salt));
        dbUserMapper.regedit(dbUser);
        return 1;
    }

    /**
     * 修改密码
     * @param username
     * @param newPassword
     * @param currentPassword
     * @return
     * @throws Exception
     */
    @Override
    public String changePassword(String username, String newPassword, String currentPassword) throws Exception {
        //1.查询当前的用户
        DbUser dbUser = dbUserMapper.selectDbUSerByUsername(username);
        if (dbUser==null){
            throw new Exception("用户不存在");
        }
        byte[] oldVerifier = VerifierCalculator.calculateVerifier(username, currentPassword, dbUser.getSalt());
        boolean equals = Arrays.equals(oldVerifier, dbUser.getVerifier());
        if (equals){
            byte[] newVerifier = VerifierCalculator.calculateVerifier(username, newPassword, dbUser.getSalt());
            dbUserMapper.upDateVerifier(username,newVerifier);
            return "修改成功";
        }else {
            throw new Exception("修改失败，用用户名不正确");
        }

    }
}
