package org.example.WallpaperManagement.service;

import org.example.WallpaperManagement.entity.User;
import org.example.WallpaperManagement.mapper.UserMapper;
import org.example.WallpaperManagement.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 0：账号不正确
     * 1：账号被封禁
     * 2：密码不正确
     * 3：登录成功
     * @param userName
     * @param password
     * @param session
     * @return
     */
    public Integer login(String userName, String password, HttpSession session) {
        User user = userMapper.getUserByUserName(userName);
        if (user == null) {
            return 0;
        }
        if (user.getStatus() == 1) {
            return 1;
        }
        if (!user.getPassword().equals(MD5Utils.getPwd(password))) {
            return 2;
        }
        session.setAttribute("user", user);
        return 3;
    }

    /**
     * 0 账号已被注册
     * 1 注册成功
     * @param user
     * @return
     */
    public Integer signIn(User user) {
        if (userMapper.getUserByUserName(user.getUserName()) != null) {
            return 0;
        }
        user.setPassword(MD5Utils.getPwd(user.getPassword()));
        return userMapper.addUser(user);
    }

    public Integer logout(HttpSession session) {
        session.invalidate();
        return 1;
    }

    public User getCurrentInfo(Integer id) {
        return userMapper.getCurrentInfo(id);
    }

    /**
     * 修改密码
     * 如果原密码正确，则修改新密码
     * 2 原密码错误
     * 0 修改失败
     * 1 修改成功
     */
    public Integer updatePassword(String oldpass, String newpass, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (!user.getPassword().equals(MD5Utils.getPwd(oldpass))) {
            return 2;
        }
        Integer result = userMapper.updatePassword(user.getId(), MD5Utils.getPwd(newpass));
        if (result == 1) {
            // 修改成功，应该取出会话标记
            session.removeAttribute("user");
        }
        return result;
    }

    public Integer updateCurrentUser(User user) {
        return userMapper.updateCurrentUser(user);
    }

}
