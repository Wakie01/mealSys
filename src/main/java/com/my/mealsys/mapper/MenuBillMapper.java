package com.my.mealsys.mapper;

import com.my.mealsys.entity.MenuBill;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuBillMapper {
    int insert(MenuBill record);

    List<MenuBill> selectAll();
}