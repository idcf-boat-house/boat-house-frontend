package com.idcf.boathouse.product.models;

/**
 * @author yan.wang
 */
public  class ShopCartPut {
    public Integer userId;
    public Integer foodID;
    public Integer num;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFoodID() {
        return foodID;
    }

    public void setFoodID(Integer foodID) {
        this.foodID = foodID;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
