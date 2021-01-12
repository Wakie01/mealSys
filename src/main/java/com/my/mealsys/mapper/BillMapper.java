package com.my.mealsys.mapper;

import com.my.mealsys.entity.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bill record);

    Bill selectByPrimaryKey(Integer id);

    List<Bill> selectAll();

    int updateByPrimaryKey(Bill record);
}