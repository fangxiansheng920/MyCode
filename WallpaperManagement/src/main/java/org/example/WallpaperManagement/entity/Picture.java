package org.example.WallpaperManagement.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Picture {
    private Integer id;
    private String fileName;
    private Integer upUserId;
    private String upTime;
    private String introduction;
    private String name;
}
