package com.my.mealsys.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryBillInfo {
    private Integer id;
    private Integer deskId;
    private String orderTime;
    private Double benefit;

}
