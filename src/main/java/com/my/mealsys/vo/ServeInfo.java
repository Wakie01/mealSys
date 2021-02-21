package com.my.mealsys.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServeInfo {
    private Integer menuId;
    private String name;
    private Integer quantity;
    private Boolean finish;
    private Double price;
}
