package org.example.WallpaperManagement;

import org.apache.ibatis.annotations.Mapper;
import org.example.WallpaperManagement.utils.MD5Utils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@MapperScan("org.example.WallpaperManagement.mapper")
@EnableScheduling
public class App {
    public static void main(String[] args) {
        //调用内部嵌入的服务器代码
        SpringApplication.run(App.class, args);
    }
}
