# Boat House（船屋餐饮系统）


## 一键启动命令：

`
docker-compose up -d
`

## 功能服务

船屋餐饮系统采用微服务架构设计，包含三条业务条线（统计服务、产品服务、账户服务），每一个业务条线可以独立的开发以及部署。

![markdown](/images/boathouse-structure.png "markdown")

#### 代码说明：

| 文件夹  | 服务说明 | 技术架构 |
| ------------ | ------------ |------------ |
| client  | 客户端，船屋餐饮官方网站  | Boatstrap 4 + Vue + Nodejs|
| management  | 船屋餐饮后台管理系统  | Boatstrap 4 + Vue + NodeJs |
| statistics-service  | 业务条线 - 统计服务  | nodejs + dotnet + redis + postgres  |
| product-service  | 业务条线 - 产品服务  |spring boot + mysql |
| account-service  | 业务条线 - 账户服务  |spring boot + mysql |
| pipelines  | 流水线脚本 | groovy |

#### Statistics service(统计服务)

统计最受欢迎的菜品，并通过图表实时展示统计结果。

![markdown](/images/boathouse-structure-stats.png "markdown")


#### Product service(产品服务)

进行菜品管理、菜品分类管理

![markdown](/images/boathouse-structure-product.png "markdown")


#### Account service(账户服务)

## DevOps 工具链





## 环境说明：

| 地址  | 说明  | 用户名密码    |
| ------------ | ------------ | ------------ |
| http://jenkins.devopshub.cn  | Jenkins管理端  | admin/admin  |
| http://tools.devopshub.cn:8081 | Nexus  | admin/admin |
| http://tools.devopshub.cn  | Jira  | admin/admin |


