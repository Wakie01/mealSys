package com.my.mealsys.service;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface MenuBillService {

    Map getSpecificMenuBill(String billId);

    Map updateMenuBillStatus(String billId, String menuId,Integer userId,Boolean status);
}
