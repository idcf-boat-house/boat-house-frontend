

# Boat House（船屋餐饮系统）—— web前端

> 包含用户操作界面 和 管理员操作界面；

## 文档目录

+ [目录结构](#1.%20目录结构)
+ [开发/调试环境配置](#2.%20开发/调试环境配置)
  + [MAC](#2.1.%20MAC)
  + [Windows](#2.2.%20Windows)


## 1. 目录结构
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


## 2. 开发/调试环境配置
### 2.1. MAC

安装brew， 如果update速度慢，请 [参考](#1.解决brew安装速度慢的问题) 
``` mac bash
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
brew update
brew install
```

安装cnpm & node
``` mac bash
brew install cnpm
cnpm install node
```

查看版本
``` mac bash
cnpm -v
node -v
```

vue最新稳定版
``` mac bash
cnpm install vue  
```

全局安装 vue-cli
``` mac bash
cnpm install --global vue-cli
```

进入web 用户界面文件夹
``` mac bash
cd /src/client
```

安装依赖保
``` mac bash
cnpm install
```


### 2.2. Windows
``` windows

```


## 测试

## 发布

## 常见问题
### 1. 解决brew安装速度慢的问题

> 使用阿里云的git库， 替换homebrew镜像源
    
替换brew.git:
``` 
cd "$(brew --repo)”
git remote set-url origin https://mirrors.aliyun.com/homebrew/brew.git
```
 替换homebrew-core.git:
``` 
cd "$(brew --repo)/Library/Taps/homebrew/homebrew-core"
git remote set-url origin https://mirrors.aliyun.com/homebrew/homebrew-core.git
```
