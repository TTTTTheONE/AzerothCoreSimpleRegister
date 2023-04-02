package com.ltf.azerothcore.entity;

/**
 * 数据库的对象
 */
public class DbUser {
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 盐值
     */
    private byte[] salt;
    /**
     * 验证
     */
    private byte[] verifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getVerifier() {
        return verifier;
    }

    public void setVerifier(byte[] verifier) {
        this.verifier = verifier;
    }
}
