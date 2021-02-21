package com.my.mealsys.contronller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.my.mealsys.entity.Menu;
import com.my.mealsys.enums.CodeMsgEnums;
import com.my.mealsys.result.Result;
import com.my.mealsys.service.MenuService;
import com.my.mealsys.utils.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 返回菜谱
     * @param hotMenu 是否需要生成本周热门菜单
     * @param httpSession
     * @return
     */
    @PostMapping("/getMenuInfo")
    public Map getMenuInfo(@RequestParam("hotMenu")  String hotMenu, HttpSession httpSession){
        boolean generateHotMenu;
        generateHotMenu= Integer.parseInt(hotMenu)>0? true:false;
        try {
            return menuService.getMenuInfo(generateHotMenu);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    /**
     * 返回某id的菜单信息，及该菜单的月售数量
     * @param menuId
     * @param httpSession
     * @return
     */
    @PostMapping("/getSpecificMenuInfo")
    public Map getSpecificMenuInfo(@RequestParam("menuId") String menuId,HttpSession httpSession){
        try {
            return menuService.getSpecificMenuInfo(menuId);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    /**
     * 更新菜单的基本信息，除了图片
     * @param menuInfo
     * @param httpSession
     * @return
     */
    @PostMapping("/updateMenuInfo")
    public Map updateMenuInfo(@RequestBody String menuInfo,HttpSession httpSession){
        try {
            JSONObject jsonObject= JSON.parseObject(menuInfo);
            if(!jsonObject.containsKey("id") || !jsonObject.containsKey("name") ||!jsonObject.containsKey("quantity") ||
                    !jsonObject.containsKey("price") || !jsonObject.containsKey("typeId")){
                return Result.error(CodeMsgEnums.LACK_PARAM);
            }
            Integer id=jsonObject.getInteger("id");
            String name=jsonObject.getString("name");
            Integer quantity=jsonObject.getInteger("quantity");
            Double price=jsonObject.getDouble("price");
            String description=jsonObject.getString("description");
            Integer typeId=jsonObject.getInteger("typeId");
            Menu menu=new Menu(id,name,quantity,price,description,null,typeId);
            return menuService.updateMenuInfo(menu);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }


    /**
     * 添加菜单，除了图片
     * @param menuInfo
     * @param httpSession
     * @return
     */
    @PostMapping("/addMenu")
    public Map addMenu(@RequestBody String menuInfo,HttpSession httpSession){
        try {
            JSONObject jsonObject= JSON.parseObject(menuInfo);
            if(!jsonObject.containsKey("name") ||!jsonObject.containsKey("quantity") ||
                    !jsonObject.containsKey("price") || !jsonObject.containsKey("typeId") || !jsonObject.containsKey("image")){
                return Result.error(CodeMsgEnums.LACK_PARAM);
            }
            String name=jsonObject.getString("name");
            Integer quantity=jsonObject.getInteger("quantity");
            Double price=jsonObject.getDouble("price");
            String description=jsonObject.getString("description");
            Integer typeId=jsonObject.getInteger("typeId");
            String image=jsonObject.getString("image");
            Menu menu=new Menu(null,name,quantity,price,description,image,typeId);
            return menuService.addMenu(menu);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    @PostMapping("/deleteMenu")
    public Map deleteMenu(@RequestParam("id") String id,HttpSession httpSession){
        try {
            return menuService.deleteMenu(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    /**
     *
     * @param base64Code
     * @param menuId
     * @param imageId
     * @param first 用于判断数据库的image是否为空   1:空， 0不空
     * @return
     */
    @PostMapping("/uploadImage")
    public Map uploadImage(@RequestParam String base64Code,@RequestParam(required = false) Integer menuId,
                           @RequestParam(required = false) Integer imageId,@RequestParam String first){
        try {
            boolean isFirst=Integer.parseInt(first)>0;
            return menuService.uploadImage(base64Code,menuId,imageId,isFirst);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    /**
     * 清理没使用的菜单图片
     */
    @GetMapping("/clearUnusedImage")
    public void clearUnusedImage(){
        menuService.clearUnusedImage();
    }
}
