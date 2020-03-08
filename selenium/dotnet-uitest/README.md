# UI自动化测试

本项目采用Selenium Grid + Docker + Xunit + Jenkins Pipeline进行UI自动化测试



> docker-compose -f docker-compose-hub.yml up -d
> docker-compose -f docker-compose-hub.yml down

本项目下的Jenkinsfile是为了本地测试流水线时使用，当本地测试成功后，需要修改部分路径，然后将流水线命令复制到主项目的Jenkinsfile中

> 调试浏览器时，可以使用VNC Client连接到容器内部5900端口，连接密码为secret

[参考文档](https://github.com/idcf-boat-house/boat-house-devops/blob/master/docs/Selenium%E8%87%AA%E5%8A%A8%E5%8C%96UI%E6%B5%8B%E8%AF%95.md)