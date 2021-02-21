package com.my.mealsys.service.impl;

import com.my.mealsys.entity.MenuType;
import com.my.mealsys.enums.CodeMsgEnums;
import com.my.mealsys.mapper.MenuTypeMapper;
import com.my.mealsys.result.Result;
import com.my.mealsys.service.MenuTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuTypeServiceImpl implements MenuTypeService {

    @Autowired
    private MenuTypeMapper menuTypeMapper;

    @Override
    public Map getMenuTypeInfo() {
        try {
            Map<String,List> dataMap=new HashMap<>();
            List<MenuType> list= menuTypeMapper.getAllMenuTypes();
            dataMap.put("menuType",list);
            return Result.success(CodeMsgEnums.SUCCESS,dataMap);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    @Override
    public Map updateMenuType(Integer id, String name) {
        try {
            System.out.println("id: "+id+"   name:"+name);
            return menuTypeMapper.updateMenuType(id,name)>0 ? Result.success(CodeMsgEnums.SUCCESS,null):Result.error(CodeMsgEnums.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    @Override
    public Map addMenuType(List<String> nameList) {
        try {
            for(String name:nameList){
                if(menuTypeMapper.addMenuType(name)==0){
                    return Result.error(CodeMsgEnums.ERROR);
                }
            }
            return Result.success(CodeMsgEnums.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    @Override
    public Map removeMenuType(Integer id) {
        try {
            return menuTypeMapper.removeMenuType(id)>0 ? Result.success(CodeMsgEnums.SUCCESS,null):Result.error(CodeMsgEnums.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }
}
