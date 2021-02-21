package com.my.mealsys.mapper;

import com.my.mealsys.entity.MenuImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuImageMapper {

    /**
     * 插入数据，并返回该数据的id值
     * @param menuImage
     * @return
     */
    Integer insertMenuImage(MenuImage menuImage);

    @Select("select url from menuimage where id=#{imageId}")
    String getImageUrlById(Integer imageId);

    @Update("update menuimage set url=#{url} where id=#{imageId}")
    Integer updateImageUrl(Integer imageId,String url);


    /**
     * 插入图片url，并返回该数据的id值
     * @param menuImage
     * @return
     */
    Integer insertImageUrl(MenuImage menuImage);


    @Select("select url from menuimage where menuId is null")
    List<String> getUnusedImage();


    @Select("select id,url from menuimage where id=#{imageId}")
    MenuImage queryById(String imageId);

    @Update("update menuimage set menuId=#{menuId} where id=#{imageId}")
    int updateMenuId(String imageId,Integer menuId);
}