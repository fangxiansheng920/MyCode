package org.example.WallpaperManagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.WallpaperManagement.entity.User;

import javax.servlet.http.HttpSession;

@Mapper
public interface UserMapper {
    User getUserByUserName(String userName);
    Integer addUser(User user);
    Integer updatePassword(@Param("id") Integer id, @Param("password") String newpass);
    Integer updateCurrentUser(User user);
    User getCurrentInfo(Integer id);

}
