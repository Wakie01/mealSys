package com.my.mealsys.mapper;

import com.my.mealsys.entity.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BillMapper {

    @Select("select id,deskId,orderTime from bill where finish=0")
    List<Bill> queryUnfinishBill();

    @Update("update bill set finish=1,userId=#{userId} where id=#{billId}")
    int finishBill(String billId,Integer userId);

    @Select("select count(*) from bill where id=#{billId} and finish=1")
    int checkBillUnfinish(String billId);

    Integer generateBill(Bill bill);
}