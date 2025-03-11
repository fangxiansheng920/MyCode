package org.example.WallpaperManagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.WallpaperManagement.entity.Picture;

import java.util.List;

@Mapper
public interface PictureMapper {

    Integer addWallpaper(Picture picture);

    List<Picture> getAllPicture();

    List<Picture> getFavorPicture(@Param("ids") List<Integer> ids, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    List<Picture> getUserUpPicture(Integer id);

    Picture getPictureById(Integer id);

    Integer updateWallpaper(Picture picture);

    Integer deleteFileWallpaper(Integer id);

    Integer getTotalPage(List<Integer> ids);

}
