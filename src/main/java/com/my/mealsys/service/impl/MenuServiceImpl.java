package com.my.mealsys.service.impl;

import com.my.mealsys.entity.Menu;
import com.my.mealsys.entity.MenuType;
import com.my.mealsys.enums.CodeMsgEnums;
import com.my.mealsys.mapper.BillMapper;
import com.my.mealsys.mapper.MenuBillMapper;
import com.my.mealsys.mapper.MenuMapper;
import com.my.mealsys.mapper.MenuTypeMapper;
import com.my.mealsys.result.Result;
import com.my.mealsys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuTypeMapper menuTypeMapper;

    @Autowired
    private BillMapper billMapper;

    @Autowired
    private MenuBillMapper menuBillMapper;

    @Override
    public Map getMenuInfo() {
        List<MenuType> menuTypeList=menuTypeMapper.selectAll();
        List<Menu> menuList=menuMapper.selectAll();

        /**   生成热门菜单           **/


        Map<String,List> dataMap=new HashMap<>();
        dataMap.put("menuType",menuTypeList);
        dataMap.put("menu",menuList);
        dataMap.put("hotMenu",null);

        return Result.success(CodeMsgEnums.SUCCESS,dataMap);
    }























}
