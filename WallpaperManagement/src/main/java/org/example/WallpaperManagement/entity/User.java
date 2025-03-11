package org.example.WallpaperManagement.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String nickname;
    private String mail;
    private String phone;
    private String birthday;
    private String recordDay;
    private Integer status; // 0 正常  1 封禁
    private String fileName;
}
