package com.my.mealsys.mapper;

import com.my.mealsys.entity.MenuType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MenuTypeMapper {

    @Select("select * from menutype")
    List<MenuType> getAllMenuTypes();

    @Update("update menutype set name=#{name} where id=#{id}")
    int updateMenuType(Integer id,String name);

    @Insert("insert into menutype(name) values(#{name})")
    int addMenuType(String name);

    @Delete("delete from menutype where id=#{id}")
    int removeMenuType(Integer id);
}