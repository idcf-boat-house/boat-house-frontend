# UI自动化测试

本项目采用Selenium Grid + Docker + Xunit + Jenkins Pipeline进行UI自动化测试

执行以下命令，可以在本地启动selenium grid环境。本地启动sg后，可以运行单元测试代码，测试代码会将浏览器命令发送给sg,从而执行测试。

> 启动环境:docker-compose -f docker-compose-hub.yml up -d
> 停止环境:docker-compose -f docker-compose-hub.yml down

项目中chrome浏览器镜像可以采用debug版镜像，采用debug版镜像后，可以在运行测试时，使用VNC Client远程连接到容器中的浏览器，从而看到浏览器执行命令的过程，这在调试代码时非常有用。

> 调试浏览器时，可以使用VNC Client连接到容器内部5900端口，连接密码为secret，[Chrome-Debug版Dockerfile](https://github.com/SeleniumHQ/docker-selenium/blob/master/NodeChromeDebug/Dockerfile)

[参考文档](https://github.com/idcf-boat-house/boat-house-devops/blob/master/docs/Selenium%E8%87%AA%E5%8A%A8%E5%8C%96UI%E6%B5%8B%E8%AF%95.md)