package com.my.mealsys.service.impl;

import com.my.mealsys.entity.Menu;
import com.my.mealsys.entity.MenuBill;
import com.my.mealsys.entity.MenuImage;
import com.my.mealsys.entity.MenuType;
import com.my.mealsys.enums.CodeMsgEnums;
import com.my.mealsys.mapper.*;
import com.my.mealsys.result.Result;
import com.my.mealsys.service.MenuService;
import com.my.mealsys.utils.Base64Utils;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private MenuImageMapper menuImageMapper;

    @Override
    public Map getMenuInfo(boolean hotMenu) {
        Map<String, List> dataMap = new HashMap<>();

        List<MenuType> menuTypeList = menuTypeMapper.getAllMenuTypes();
        dataMap.put("menuType", menuTypeList);

        List<Menu> menuList = menuMapper.getAllMenusSimpleInfo();
        for(Menu menu:menuList){
            if(menu.getImage()!=null && menu.getImage().contains(",")){
                String imageId=menu.getImage().split(",")[0];
                String imageUrl=menuImageMapper.getImageUrlById(Integer.parseInt(imageId));
                menu.setImage(imageUrl);
            }
        }
        dataMap.put("menu", menuList);

        /**   生成热门菜单           **/
        if (hotMenu) {
            List<Menu> hotMenuList = new ArrayList<>();

            //获取前一月时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH, -1);
            Date date = calendar.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String time = dateFormat.format(date);

            //获取前一周的账单
            List<MenuBill> menuBillList = menuBillMapper.selectByTime(time);

            //对前一周的账单进行统计、排序
            List<MenuRecord> menuRecords = new ArrayList<>();
            for (MenuBill menuBill : menuBillList) {
                boolean finish = false;    //标记是否添加到menuRecords
                for (MenuRecord menuRecord : menuRecords) {
                    if (menuBill.getMenuId().equals(menuRecord.getMenuId())) {
                        menuRecord.setNum(menuRecord.getNum() + 1);
                        finish = true;
                        break;
                    }
                }
                //未添加的话
                if (!finish) {
                    menuRecords.add(new MenuRecord(menuBill.getMenuId(), 1));
                }
            }
            Collections.sort(menuRecords);


            //获取热门menu的详细信息
            for (int i = 0; i < 5 && i < menuRecords.size(); i++) {
                Menu menu = menuMapper.getMenuSimpleInfoByPrimaryKey(menuRecords.get(i).getMenuId());
                if(menu.getImage()!=null && menu.getImage().contains(",")){
                    String imageId=menu.getImage().split(",")[0];
                    String imageUrl=menuImageMapper.getImageUrlById(Integer.parseInt(imageId));
                    menu.setImage(imageUrl);
                }
                hotMenuList.add(menu);
            }

            dataMap.put("hotMenu", hotMenuList);
        }

        return Result.success(CodeMsgEnums.SUCCESS, dataMap);
    }

    @Override
    public Map getSpecificMenuInfo(String menuId) {
        try {
            Menu menu = menuMapper.getMenuInfoByPrimaryKey(Integer.parseInt(menuId));

            //获取前一个月的时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH, -1);
            Date date = calendar.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String time = dateFormat.format(date);

            //月售数量
            int monthSellCount = menuBillMapper.getSpecificMenuMonthSellCount(Integer.parseInt(menuId), time);

            //获取图片信息
            List<MenuImage> menuImageList=new ArrayList<>();
            if(menu.getImage()!=null && menu.getImage().contains(",")){
                String[] imageIds=menu.getImage().split(",");
                for(String image:imageIds){
                    MenuImage menuImage=menuImageMapper.queryById(image);
                    menuImageList.add(menuImage);
                }
            }

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("menu", menu);
            dataMap.put("monthSellCount", monthSellCount);
            dataMap.put("menuImageList",menuImageList);
            return Result.success(CodeMsgEnums.SUCCESS, dataMap);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            CodeMsgEnums err = CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }

    }

    @Override
    public Map updateMenuInfo(Menu menu) {
        try {
            return menuMapper.updateMenuInfo(menu) > 0 ? Result.success(CodeMsgEnums.SUCCESS, null) : Result.error(CodeMsgEnums.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err = CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    @Override
    public Map addMenu(Menu menu) {
        try {
            Integer menuId=menuMapper.addMenu(menu);
            if(menu.getImage()!=null && menu.getImage().contains(",")){
                String[] imageIds=menu.getImage().split(",");
                for(String imageId:imageIds){
                    menuImageMapper.updateMenuId(imageId,menu.getId());
                }
            }
            return Result.success(CodeMsgEnums.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err = CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    @Override
    public Map deleteMenu(Integer id) {
        try {
            return menuMapper.deleteMenuById(id) > 0 ? Result.success(CodeMsgEnums.SUCCESS, null) : Result.error(CodeMsgEnums.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err = CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    @Override
    public Map uploadImage(String base64Code, Integer menuId, Integer imageId,boolean isFirst) {
        //判断图片类型
        String type = Base64Utils.checkImageBase64Format(base64Code);
        if (type == null) {
            return Result.error(CodeMsgEnums.IMAGE_UPLOAD_FAIL);
        }
        //图片保存路径，需自行设置
        String filePath = "D:/Java workspace/mealSys/src/main/resources/menusImg/";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
        String fileName = simpleDateFormat.format(new Date()) + "." + type;

        try {
            //保存文件
            Base64Utils.convertBase64DataToImage(base64Code, filePath + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String,Object> returnMap=new HashMap<>();
        returnMap.put("imageUrl",fileName);

        if (menuId != null) {      //菜单已经新建
            if(imageId!=null){      //替换旧的图片
                //获取旧文件的url
                String oldFileUrl=menuImageMapper.getImageUrlById(imageId);
                //删除旧的文件
                new File(filePath+oldFileUrl).delete();
                //根据imageId对menuImage的url进行更新
                menuImageMapper.updateImageUrl(imageId,fileName);
                returnMap.put("imageId",imageId);

            }else{    //添加新的图片
                //将image的url与menuId插入到menuImage上，返回imageId,返回的imageId在menuImage里
                MenuImage menuImage = new MenuImage(menuId, fileName);
                menuImageMapper.insertMenuImage(menuImage);
                //根据menuId将imageId append到menu的image上
                if(isFirst){
                    menuMapper.insertMenuImageOfFirst(menuImage);
                }else{
                    menuMapper.insertMenuImage(menuImage);
                }
                returnMap.put("imageId",menuImage.getId());
            }
        } else {     //并无菜单
            //将image url插入到menuImage，返回imageId,返回的imageId在menuImage里
            MenuImage menuImage=new MenuImage();
            menuImage.setUrl(fileName);
            menuImageMapper.insertImageUrl(menuImage);
            returnMap.put("imageId",menuImage.getId());
        }
        return Result.success(CodeMsgEnums.SUCCESS,returnMap);

    }


    @Override
    public void clearUnusedImage() {
        List<String> urlList=menuImageMapper.getUnusedImage();
        //图片文件路径
        String filePath = "D:/Java workspace/mealSys/src/main/resources/menusImg/";
        for(String url:urlList){
            new File(filePath+url).delete();
        }
    }
}

class MenuRecord implements Comparable<MenuRecord> {
    private int menuId;
    private int num;

    public MenuRecord() {
    }

    public MenuRecord(int menuId, int num) {
        this.menuId = menuId;
        this.num = num;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public int compareTo(MenuRecord o) {
        return o.getNum() - this.num;
    }
}
