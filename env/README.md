# IDCF 社区共创DevOps计划 

## 环境：

### 公共环境

#### 服务器说明

| 地址  | 说明  | 登陆方式    | 密码    |
| ------------ | ------------ | ------------ | ------------ |
| jenkins.devopshub.cn  | Jenkins服务器  | ssh localadmin@jenkins.devopshub.cn | 标准复杂  |
| tools.devopshub.cn | 工具服务器，包括：SonarQube,Nexus  | ssh localadmin@tools.devopshub.cn | 标准复杂 |
| 138.91.37.88 | 流水线开发测试服务器，本机部署测试 |  ssh localadmin@tools.devopshub.cn | 标准复杂 |
| k8s.devopshub.cn  | K8s服务器 | ssh -i <ssh_key_file_path> localadmin@k8s.devopshub.cn | SSH key, 位置 \env\k8s\ssh_key\id_rsa |

#### 已部署工具

| 地址  | 说明  | 用户名密码 |
| ------------ | ------------ | ------------ | 
| http://jenkins.devopshub.cn  | Jenkins管理端  |  admin/标准复杂 | 
| http://tools.devopshub.cn:8081 | Nexus  |  admin/标准复杂 |   
| http://tools.devopshub.cn:9000| Sonarqube  |  admin/标准复杂 |   
| http://tools.devopshub.cn  | Jira  |  admin/标准复杂 |   

### 私有环境搭建
