# Boat House（船屋餐饮系统）

![Build client](https://github.com/idcf-boat-house/boat-house/workflows/Build%20client/badge.svg)
![Build statistics-service](https://github.com/idcf-boat-house/boat-house/workflows/Build%20statistics-service/badge.svg)
![Build product-service](https://github.com/idcf-boat-house/boat-house/workflows/Build%20product-service/badge.svg)
![Build management](https://github.com/idcf-boat-house/boat-house/workflows/Build%20management/badge.svg)

## 功能服务

船屋餐饮系统采用微服务架构设计，包含五条业务条线（统计服务、商品服务、账户服务、订单服务、支付服务），每一个业务条线可以独立的开发以及部署。

![markdown](/images/boathouse-structure.png "markdown")

#### 代码说明：  

| 文件夹  | 服务说明 | 技术架构 |
| ------------ | ------------ |------------ |
| client  | 客户端，船屋餐饮官方网站  | Boatstrap 4 (模版：AppStrap [参考链接](http://demos.themelize.me/appstrap3.3.3/theme/intro.html "参考链接"))  <br> Vue + Nodejs|
| management  | 船屋餐饮后台管理系统  | Boatstrap 4 (模版：ModernAdmin [参考链接](https://preview.themeforest.net/item/modern-admin-clean-bootstrap-4-dashboard-html-template/full_screen_preview/21430660?_ga=2.66676205.272140448.1583930719-396544145.1583854564 "参考链接"))  <br> Vue + NodeJs |
| statistics-service  | 业务条线 - 统计服务  | nodejs + dotnet + redis + postgres  |
| product-service  | 业务条线 - 产品服务  |spring boot + mysql |
| account-service  | 业务条线 - 账户服务  |spring boot + mysql |
| order-service  | 业务条线 - 订单服务  |spring boot + mysql |
| payment-service  | 业务条线 - 支付服务  |spring boot + mysql |
| pipelines  | 流水线脚本 | groovy |

#### Statistics service(统计服务)

统计最受欢迎的菜品，并通过图表实时展示统计结果。

![markdown](/images/boathouse-structure-stats.png "markdown")


#### Product service(产品服务)

进行菜品管理、菜品分类管理

![markdown](/images/boathouse-structure-product02.png "markdown")


#### Account service(账户服务)

## 本地开发调试

本项目采用容器的方式进行编译、打包、以及运行，客户端安装Docker以及Docker-compose工具后可以一键运行此应用。

`
docker-compose up -d
`

## DevOps 工具链

本项目使用JIRA进行项目管理、Github代码管理、Jenkins持续集成、Nexus进行包管理以及容器镜像管理、Jemeter进行接口测试以及压力测试、Selenium进行自动化UI测试。下图是本项目的持续交付流水线架构：

![markdown](/images/boathouse-structure-tools.png "markdown")

## ChatOps && AIOps

本项目将支持ChatOps && AIOps，让开发、测试、客户可以在IM即时通讯工具里以聊天的方式完成相应的工作。

![markdown](/images/boathouse-structure-chatops.png "markdown")


#### DevOps工具链环境：

| 地址  | 说明  | 
| ------------ | ------------ | 
| http://jenkins.devopshub.cn  | Jenkins管理端  |
| http://tools.devopshub.cn:8081 | Nexus  | 
| http://tools.devopshub.cn:9000| Sonarqube|
| http://tools.devopshub.cn  | Jira  |


