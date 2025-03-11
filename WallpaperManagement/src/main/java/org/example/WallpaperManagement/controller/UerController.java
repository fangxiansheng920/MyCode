package org.example.WallpaperManagement.controller;

import org.example.WallpaperManagement.entity.User;
import org.example.WallpaperManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class UerController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    public Integer login(String userName, String password, HttpSession session) {
        return userService.login(userName, password, session);
    }

    @RequestMapping("/signin.do")
    public Integer signIn(User user) {
        return userService.signIn(user);
    }

    @RequestMapping("/logout.action")
    public Integer logout(HttpSession session) {
        return userService.logout(session);
    }

    @RequestMapping("/getCurrentInfo.action")
    public User getCurrentInfo(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return userService.getCurrentInfo(user.getId());
    }

    @RequestMapping("/updatePassword.action")
    public Integer updatePassword(String oldpass, String newpass, HttpSession session) {
        return userService.updatePassword(oldpass, newpass, session);
    }

    //文件上传
    @RequestMapping("/fileUpload.action")
    public String fileUpload(@RequestParam("picture") MultipartFile file, HttpSession session) throws IOException, URISyntaxException {
        User user = (User) session.getAttribute("user");
        File f = new File("C:/Users/28117/Desktop/pictures/avatars/" + user.getUserName() + ".jpg");
        file.transferTo(f);
        return user.getUserName() + ".jpg";
    }

    @RequestMapping("/updateCurrentUser.action")
    public Integer updateCurrentUser(User user) {
        return userService.updateCurrentUser(user);
    }

    @RequestMapping("/getUserById.action")
    public User getUserById(Integer id) {
        return userService.getCurrentInfo(id);
    }

    @RequestMapping("/judgeUser.action")
    public Integer judgeUser(Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getId() == id) return 1;
        return 0;
    }

}
