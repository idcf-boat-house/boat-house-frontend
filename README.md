船屋示例代码
=========


## 一键启动命令：

`
docker-compose -f docker-compose.yml -f docker-compose-standalone.yml up -d
`

docker exec -it 9baa65a75aaf redis-cli

lrange votes 0 -1