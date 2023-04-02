package com.ltf.azerothcore.controller;

import com.ltf.azerothcore.service.IUserService;
import com.ltf.azerothcore.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;


    @RequestMapping("/regedit")
    public R regedit(String username, String password)  {
        try {
            userService.regedit(username, password);
            return R.ok(null,"操作完成");
        } catch (Exception e) {

            return R.fail("操作失败");
        }

    }
    @RequestMapping("/change-password")
    public R changePassword(String username,String newPassword,String currentPassword)  {
        try {
            userService.changePassword(username,newPassword,currentPassword);
            return R.ok(null,"操作完成");
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("操作失败");
        }

    }
}
