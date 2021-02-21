package com.my.mealsys.mapper;

import com.my.mealsys.entity.MenuBill;
import com.my.mealsys.vo.HistoryBillInfo;
import com.my.mealsys.vo.MenuBillInfo;
import com.my.mealsys.vo.ServeInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuBillMapper {

    @Select("select menuId from menubill where menuTime>#{time}")
    List<MenuBill> selectByTime(String time);

    @Select("select menubill.menuId,menu.name,menu.price,menubill.quantity,menubill.finish " +
            "FROM menubill left join menu on menubill.menuId=menu.id where menubill.billId=#{billId}")
    List<ServeInfo> selectByBillId(String billId);

    @Update("update menubill set finish=#{status},userId=#{userId},menuTime=#{menuTime} where billId=#{billId} and menuId=#{menuId}")
    int updateMenuBillStatus(String billId,String menuId,Integer userId,Boolean status,String menuTime);

    @Select("select count(*) from menubill where menuId=#{menuId} and menuTime>=#{menuTime}")
    int getSpecificMenuMonthSellCount(Integer menuId,String menuTime);

    @Select("select B.id,B.deskId,B.orderTime,SUM(A.quantity*C.price) as benefit " +
            "from menubill as A " +
            "LEFT JOIN bill as B ON A.billId=B.id " +
            "LEFT JOIN menu as C ON A.menuId=C.id " +
            "WHERE B.finish=1 and B.orderTime>=#{fromTime} and B.orderTime<=#{toTime} " +
            "GROUP BY B.id")
    List<HistoryBillInfo> getHistoryBillsInfo(String fromTime, String toTime);

    int generateMenuBill(Integer billId, List<MenuBillInfo> menuBillInfoList);

    @Select("select quantity from menubill where billId=#{billId} and menuId=#{menuId}")
    Integer getMenuQuantityByBillId(Integer billId,Integer menuId);

    @Update("update menubill set quantity=#{quantity},finish=false where billId=#{billId} and menuId=#{menuId}")
    int updateMenuQuantity(Integer billId,Integer menuId,Integer quantity);

    @Insert("insert into menubill(billId,menuId,quantity,finish) values (#{billId},#{menuId},#{quantity},false)")
    int insertMenuBill(Integer billId,Integer menuId,Integer quantity);
}