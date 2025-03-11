package org.example.WallpaperManagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/avatar/**") // 虚拟访问路径
                .addResourceLocations("file:C:/Users/28117/Desktop/pictures/avatars/");     // 虚拟路径对应磁盘路径

        registry.addResourceHandler("/wallpaper/**") // 虚拟访问路径
                .addResourceLocations("file:C:/Users/28117/Desktop/pictures/wallpapers/");     // 虚拟路径对应磁盘路径
    }
}
