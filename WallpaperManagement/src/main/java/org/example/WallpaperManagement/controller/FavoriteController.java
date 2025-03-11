package org.example.WallpaperManagement.controller;

import org.example.WallpaperManagement.entity.Favorite;
import org.example.WallpaperManagement.entity.User;
import org.example.WallpaperManagement.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @RequestMapping("/editFavorite.action")
    public Integer editFavorite(Integer isFavorited, Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        Integer pictureId = id;
        if (isFavorited == 1) {
            //执行添加操作
            return favoriteService.addFavor(userId, pictureId);
        } else if (isFavorited == 0) {
            //执行删除操作
            return favoriteService.deleteFavor(userId, pictureId);
        }
        return 0;
    }

    @RequestMapping("/getFavoriteById.action")
    public Integer getFavoriteById(Integer pictureId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return favoriteService.getFavoriteById(user.getId(), pictureId);
    }
}
