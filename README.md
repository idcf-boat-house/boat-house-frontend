# Boat House（船屋餐饮系统）—— web前端

> 包含用户操作界面 和 管理员操作界面；

@[TOC](文章目录)


## 目录结构
```
├── images                              # 代码库中饮用图片的统一存放文件夹
├── src                                 # 代码文件
│   ├── client                          # 用户操作界面
│   │   ├── build
│   │   ├── config
│   │   │   ├── dev.env.js              # 开发环境配置文件
│   │   │   ├── index.js                #webpack相关
│   │   │   ├── prod.env.js             #生产环境配置文件，一idcf社区为准不建议修改
│   │   │   ├── test.env.js             #测试环境配置文件，一idcf社区为准不建议修改
│   │   ├── mock                       # 项目mock 模拟数据
│   │   ├── plop-templates             # 基本模板
│   │   ├── public                     # 静态资源
│   │   │   │── favicon.ico            # favicon图标
│   │   │   └── index.html             # html模板
│   │   ├── src                        # 源代码
│   │   │   ├── api                    # 所有请求
│   │   │   ├── assets                 # 主题 字体等静态资源
│   │   │   ├── components             # 全局公用组件
│   │   │   ├── directive              # 全局指令
│   │   │   ├── filters                # 全局 filter
│   │   │   ├── icons                  # 项目所有 svg icons
│   │   │   ├── lang                   # 国际化 language
│   │   │   ├── layout                 # 全局 layout
│   │   │   ├── router                 # 路由
│   │   │   │   ├──index.js           # 路由配置文件
│   │   │   │   ├──modules            # 存储各个模块的路由信息
│   │   │   ├── store                  # 全局 store管理
│   │   │   ├── styles                 # 全局样式
│   │   │   ├── utils                  # 全局公用方法
│   │   │   ├── vendor                 # 公用vendor
│   │   │   ├── views                  # views 所有页面
│   │   │   ├── App.vue                # 入口页面
│   │   │   ├── main.js                # 入口文件 加载组件 初始化等
│   │   │   └── permission.js          # 权限管理
│   │   ├── tests                      # 测试
│   │   ├── .env.xxx                   # 环境变量配置
│   │   ├── .eslintrc.js               # eslint 配置项
│   │   ├── .babelrc                   # babel-loader 配置
│   │   ├── .travis.yml                # 自动化CI配置
│   │   ├── vue.config.js              # vue-cli 配置
│   │   ├── postcss.config.js          # postcss 配置
│   │   └── package.json               # package.json
│   └── management                      # 与 client 类似  
├── test
│   ├── jmeter
│   ├── selenium
│   │   └── dotnet-uitest
│   ├── unit-test
│   │   ├── client-test
│   │   └── management-test
```


## 开发/调试环境配置
### MAC
``` mac

```
### Windows
``` windows

```


## 测试

## 发布