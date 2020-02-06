船屋示例代码
=========


## 一键启动命令：

`
docker-compose -f docker-compose.yml -f docker-compose-standalone.yml up -d
`

docker exec -it 9baa65a75aaf redis-cli

lrange votes 0 -1


## 代码说明：

| 文件夹  | 服务  | 技术架构 |
| ------------ | ------------ |------------ |
| client/web  | 客户端，船屋餐饮官方网站  | Boatstrap 4 + Vue + Python|
| client/mobile  | 移动端，船屋点餐系统  | 待确认 |
| management  | 船屋餐饮后台管理系统  | Boatstrap 4 + Vue + NodeJs |
| statistics-service  | 业务条线 - 统计服务  | nodejs + dotnet + redis + postgres  |
| product-service  | 业务条线 - 产品服务  |spring boot + mysql |
| account-service  | 业务条线 - 账户服务  |spring boot + mysql |
| pipelines  | 流水线脚本 | groovy |


## 环境说明：

| 地址  | 说明  | 用户名密码    |
| ------------ | ------------ | ------------ |
| http://jenkins.devopshub.cn  | Jenkins管理端  | admin/admin  |
| http://tools.devopshub.cn:8081 | Nexus  | admin/admin |
| http://tools.devopshub.cn  | Jira  | admin/admin |
