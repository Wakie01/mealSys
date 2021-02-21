package com.my.mealsys.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuBillInfo {
    private Integer menuId;
    private Integer quantity;
}
