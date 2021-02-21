package com.my.mealsys.service;

import com.my.mealsys.entity.Menu;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface MenuService {

    Map getMenuInfo(boolean hotMenu);

    Map getSpecificMenuInfo(String menuId);

    Map updateMenuInfo(Menu menu);

    Map addMenu(Menu menu);

    Map deleteMenu(Integer id);

    Map uploadImage(String base64Code, Integer menuId, Integer imageId,boolean isFirst);

    void clearUnusedImage();
}
