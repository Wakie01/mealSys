package com.my.mealsys.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface MenuTypeService {

    Map getMenuTypeInfo();

    Map updateMenuType(Integer id, String name);

    Map addMenuType(List<String> nameList);

    Map removeMenuType(Integer id);
}
