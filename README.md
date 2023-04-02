# 使用

你需要修改一下配置文件的数据库地址和账号密码即可，十分的简单易用。
```yaml
spring:
  datasource:
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://{you ip}:{you port}/acore_auth?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
      username: {username}
      password: {password}
```