package com.ltf.azerothcore.utils;

import java.security.SecureRandom;

/**
 * 随机数组
 */
public class RandomBytes {

    /**
     * 说生成32位的随机数组
     * @return
     */
    public static byte[] random32Bytes() {
        SecureRandom random = new SecureRandom();
        byte[] byteArray = new byte[32];
        random.nextBytes(byteArray);
        return byteArray;
    }
}

