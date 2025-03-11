package org.example.WallpaperManagement.controller;

import com.github.pagehelper.PageInfo;
import org.example.WallpaperManagement.entity.Picture;
import org.example.WallpaperManagement.entity.User;
import org.example.WallpaperManagement.mapper.PictureMapper;
import org.example.WallpaperManagement.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/addWallpaper.action")
    private Integer addWallpaper(Picture picture, HttpSession session) {
        User user = (User) session.getAttribute("user");
        picture.setUpUserId(user.getId());
        return pictureService.addWallpaper(picture);
    }

    @RequestMapping("/fileUploadWallpaper.action")
    public String fileUploadWallpaper(@RequestParam("picture") MultipartFile file, HttpSession session) throws IOException, URISyntaxException {
        User user = (User) session.getAttribute("user");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String formattedTime = now.format(formatter);
        File f = new File("C:/Users/28117/Desktop/pictures/wallpapers/" + user.getUserName() + "_" + formattedTime + ".jpg");
        file.transferTo(f);
        return user.getUserName() + "_"  + formattedTime + ".jpg";
    }

    /**
     * 不修改文件名字
     * 直接替换文件
     */
    @RequestMapping("/editFileWallpaper.action")
    public Integer editFileWallpaper(@RequestParam("picture") MultipartFile file, @RequestParam("fileName") String fileName) throws IOException, URISyntaxException {
        File f = new File("C:/Users/28117/Desktop/pictures/wallpapers/" + fileName);
        file.transferTo(f);
        return 1;
    }

    @RequestMapping("/getAllPicture.action")
    private PageInfo<Picture> getAllPicture(Integer pageIndex) {
        return pictureService.getAllPicture(pageIndex);
    }

    @RequestMapping("/getFavorPicture.action")
    private PageInfo<Picture> getFavorPicture(Integer pageIndex, HttpSession session) {
        return pictureService.getFavorPicture(pageIndex, session);
    }

    @RequestMapping("/getUserUpPicture.action")
    private PageInfo<Picture> getUserUpPicture(Integer pageIndex, HttpSession session) {
        return pictureService.getUserUpPicture(pageIndex, session);
    }

    @RequestMapping("/getPictureById.action")
    private Picture getPictureById(Integer id) {
        return pictureService.getPictureById(id);
    }

    @RequestMapping("/updateWallpaper.action")
    public Integer updateWallpaper(Picture picture) {
        return pictureService.updateWallpaper(picture);
    }

    @RequestMapping("/deleteFileWallpaper.action")
    public Integer deleteFileWallpaper(String fileName, Integer id) {
        File f = new File("C:/Users/28117/Desktop/pictures/wallpapers/" + fileName);
        if (f.exists()) {
            f.delete();
        }
        return pictureService.deleteFileWallpaper(id);
    }
}
