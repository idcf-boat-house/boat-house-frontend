<!--
 * @Author: kenan
 * @Date: 2021-09-27 17:03:28
 * @LastEditors: kenan
 * @LastEditTime: 2021-09-27 17:10:18
 * @Description: file content
-->

```bash
docker run -d --name ubuntu-node61 -e ROOT_PASSWORD=root123  -v "$(pwd):/home/project" -p 22:22  -p 3000:3000 -p 8080:8080 registry.cn-hangzhou.aliyuncs.com/smartide/smartide-node:ubuntu
```
