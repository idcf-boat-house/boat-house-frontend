## Boot House Product Service API
### 简介：
Spring Boot + MySql 提供的 Web RESTFUL API 用以进行 Boot House 网站的管理

### API 

#### 1. 菜品分类
##### 添加菜品分类：

http://[DOMAIN]/api/v1.0/BoatHouse/AddFoodCategory?name=[NAME]

##### 删除菜品分类：

http://[DOMAIN]/api/v1.0/BoatHouse/DeleteFoodCategory?name=[NAME]

##### 更新菜品分类：

http://[DOMAIN]/api/v1.0/BoatHouse/UpdateFoodCategory?id=[ID]&name=[NAME]

##### 查询菜品分类列表：

http://[DOMAIN]/api/v1.0/BoatHouse/GetFoodCategories

##### 查询单个菜品分类：

http://[DOMAIN]/api/v1.0/BoatHouse/GetFoodCategory?id=[ID]