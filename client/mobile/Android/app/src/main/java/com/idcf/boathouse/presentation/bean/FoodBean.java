package com.idcf.boathouse.presentation.bean;

import java.util.List;

public class FoodBean {


    /**
     * data : [{"CategoryId": 1,"Description": "圣亨利","Price": 100,"Picture": "","Id": 1,"Name": "红酒"},]
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {
        /**
         "CategoryId": 1,
         "Description": "圣亨利",
         "Price": 100,
         "Picture": "",
         "Id": 1,
         "Name": "红酒"
         */

        private List<DatasBean> datas;

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             "CategoryId": 1,
             "Description": "圣亨利",
             "Price": 100,
             "Picture": "",
             "Id": 1,
             "Name": "红酒"
             */

            private int CategoryId;
            private String Description;
            private int Price;
            private String Picture;
            private int Id;
            private String Name;

            public int getCategoryId() {
                return CategoryId;
            }

            public void setCategoryId(int categoryId) {
                this.CategoryId = categoryId;
            }

            public String getDescription() {
                return Description;
            }

            public void setDescription(String description) {
                this.Description = description;
            }

            public int getPrice() {
                return Price;
            }

            public void setPrice(int price) {
                this.Price = price;
            }

            public String getPicture() {
                return Picture;
            }

            public void setPicture(String picture) {
                this.Picture = picture;
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
}
