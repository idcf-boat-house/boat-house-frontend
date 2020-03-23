package com.idcf.boathouse.presentation.bean;

import java.util.List;

public class FoodCategoryBean {


    /**
     * data : [{"Description":"酒水","Id":1,"Name":"酒水"},{"Description":"凉菜","Id":2,"Name":"凉菜"}]
     * errcode : 0
     * message :
     */

    private int errcode;
    private String message;
    private List<DataBean> data;

    public int getErrorCode() {
        return errcode;
    }

    public void setErrorCode(int errorCode) {
        this.errcode = errorCode;
    }

    public String getErrorMsg() {
        return message;
    }

    public void setErrorMsg(String errorMsg) {
        this.message = errorMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Description : 酒水
         * Id : 1
         * Name : 酒水
         */

        private String Description;
        private int Id;
        private String Name;

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            this.Description = description;
        }

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            this.Id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            this.Name = name;
        }

    }
}
