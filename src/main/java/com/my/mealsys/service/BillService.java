package com.my.mealsys.service;

import com.my.mealsys.vo.MenuBillInfo;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface BillService {

    Map getUnfinishBill();

    Map getHistoryBills(String fromTime,String toTime);

    Map finishBill(String billId,Integer userId);

    Map generateBill(Integer deskId, List<MenuBillInfo> menuBillInfoList);

    Map appendBill(Integer billId, List<MenuBillInfo> menuBillInfoList);
}
