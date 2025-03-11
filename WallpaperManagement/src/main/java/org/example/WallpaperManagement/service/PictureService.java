package org.example.WallpaperManagement.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.WallpaperManagement.entity.Picture;
import org.example.WallpaperManagement.entity.User;
import org.example.WallpaperManagement.mapper.FavoriteMapper;
import org.example.WallpaperManagement.mapper.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class PictureService {
    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Value("${pageSize}")
    private Integer pageSize;

    public Integer addWallpaper(Picture picture) {
        return pictureMapper.addWallpaper(picture);
    }

    public PageInfo<Picture> getAllPicture(Integer pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Picture> pictureList = pictureMapper.getAllPicture();
        return new PageInfo<Picture>(pictureList);
    }

    public PageInfo<Picture> getFavorPicture(Integer pageIndex, HttpSession session) {
        // 获取当前登录的用户
        User user = (User) session.getAttribute("user");
        // 获取当前用户收藏的图片 ID 列表
        List<Integer> pictureIdList = favoriteMapper.getFavorId(user.getId());
        // 如果收藏列表为空，直接返回空的 PageInfo
        if (pictureIdList == null || pictureIdList.isEmpty()) {
            return new PageInfo<>(new ArrayList<>());
        }
        // 获取总记录数
        int total = pictureMapper.getTotalPage(pictureIdList);
        // 计算总页数
        int pages = (int) Math.ceil((double) total / pageSize);
        // 如果页码大于总页数，调整页码为总页数
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageIndex > pages) {
            pageIndex = pages;
        }
        // 计算分页偏移量
        int offset = (pageIndex - 1) * pageSize;
        // 获取当前分页的图片列表
        List<Picture> pictureList = pictureMapper.getFavorPicture(pictureIdList, offset, pageSize);
        // 创建 PageInfo 对象，并手动设置分页相关信息
        PageInfo<Picture> pageInfo = new PageInfo<>(pictureList);
        pageInfo.setPageNum(pageIndex); // 当前页码
        pageInfo.setTotal(total); // 总记录数
        pageInfo.setPageSize(pageSize); // 每页记录数
        pageInfo.setPages(pages); // 总页数

        return pageInfo;
    }

    public PageInfo<Picture> getUserUpPicture(Integer pageIndex, HttpSession session) {
        PageHelper.startPage(pageIndex, pageSize);
        User user = (User) session.getAttribute("user");
        List<Picture> pictureList = pictureMapper.getUserUpPicture(user.getId());
        return new PageInfo<Picture>(pictureList);
    }

    public Picture getPictureById(Integer id) {
        return pictureMapper.getPictureById(id);
    }

    public Integer updateWallpaper(Picture picture) {
        return pictureMapper.updateWallpaper(picture);
    }

    public Integer deleteFileWallpaper(Integer id) {
        return pictureMapper.deleteFileWallpaper(id);
    }
}
