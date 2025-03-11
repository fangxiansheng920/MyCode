package org.example.WallpaperManagement.service;

import org.example.WallpaperManagement.entity.Favorite;
import org.example.WallpaperManagement.mapper.FavoriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;
    public Integer addFavor(Integer userId, Integer pictureId) {
        return favoriteMapper.addFavor(userId, pictureId);
    }


    public Integer deleteFavor(Integer userId, Integer pictureId) {
        return favoriteMapper.deleteFavor(userId, pictureId);
    }

    public Integer getFavoriteById(Integer userId, Integer pictureId) {
        return favoriteMapper.getFavoriteById(userId, pictureId);
    }
}
