package com.my.mealsys.contronller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.my.mealsys.enums.CodeMsgEnums;
import com.my.mealsys.result.Result;
import com.my.mealsys.service.MenuTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class MenuTypeController {

    @Autowired
    private MenuTypeService menuTypeService;

    /**
     * 获取菜单类型信息
     * @param httpSession
     * @return
     */
    @PostMapping("getMenuTypeInfo")
    public Map getMenuTypeInfo(HttpSession httpSession){
        try {
            return menuTypeService.getMenuTypeInfo();
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    /**
     * 更新菜单类型信息
     * @param id
     * @param name
     * @param httpSession
     * @return
     */
    @PostMapping("updateMenuType")
    public Map updateMenuType(@RequestParam("id") String id,@RequestParam("name") String name,HttpSession httpSession){
        try {
            return menuTypeService.updateMenuType(Integer.parseInt(id),name);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    /**
     * 增添菜单类型
     * @param names
     * @param httpSession
     * @return
     */
    @PostMapping("addMenuType")
    public Map addMenuType(@RequestBody String names, HttpSession httpSession){
        try {
            JSONObject jsonObject= JSON.parseObject(names);
            if(!jsonObject.containsKey("names")){
                return Result.error(CodeMsgEnums.LACK_PARAM);
            }
            List<String> nameList=(List<String>)jsonObject.get("names");
            return menuTypeService.addMenuType(nameList);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    @PostMapping("removeMenuType")
    public Map removeMenuType(@RequestParam("id") String id,HttpSession httpSession){
        try {
            return menuTypeService.removeMenuType(Integer.parseInt(id));
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }
}
