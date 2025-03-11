package org.example.WallpaperManagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.WallpaperManagement.entity.Favorite;
import org.example.WallpaperManagement.entity.Picture;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    Integer addFavor(@Param("userId") Integer userId, @Param("pictureId") Integer pictureId);

    Integer deleteFavor(@Param("userId") Integer userId, @Param("pictureId") Integer pictureId);

    Integer getFavoriteById(@Param("userId") Integer userId, @Param("pictureId") Integer pictureId);

    List<Integer> getFavorId(Integer userId);
}
