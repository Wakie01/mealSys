package com.my.mealsys.mapper;

import com.my.mealsys.entity.MenuType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuType record);

    MenuType selectByPrimaryKey(Integer id);

    List<MenuType> selectAll();

    int updateByPrimaryKey(MenuType record);
}