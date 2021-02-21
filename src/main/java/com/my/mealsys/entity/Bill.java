package com.my.mealsys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    private Integer id;

    private Integer deskId;

    private String orderTime;

    private Boolean finish;

    private Integer userId;

    public Bill(Integer deskId, String orderTime, Boolean finish) {
        this.deskId = deskId;
        this.orderTime = orderTime;
        this.finish = finish;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeskId() {
        return deskId;
    }

    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}