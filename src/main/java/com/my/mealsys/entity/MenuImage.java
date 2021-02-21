package com.my.mealsys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuImage {
    private Integer id;

    private Integer menuId;

    private String url;

    public MenuImage(Integer menuId, String url) {
        this.menuId = menuId;
        this.url = url;
    }
}