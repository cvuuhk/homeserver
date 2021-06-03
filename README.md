# README

## 环境配置

本物联网平台在ArchLinux 5.12.4.arch1-2平台下使用Intellij IDEA ultimate开发，采用的数据库是 Mariadb 10.5.10-1 版本，MQTT Broker 使用的是 Mosquitto 2.0.10-2 版本。

1. 首先在笔记本电脑上安装 ArchLinux 操作系统，之后配置软件包镜像并安装 Mosquitto 和 MariaDB 等软件包
2. 编辑 Mosquitto 配置文件，使 Mosquitto 能够根据 username 和 password 字段进行安全验证
3. 配置 MariaDB 数据库
   1. 新建用户 cui，密码 123456
   2. 新建数据库 server 并授予用户 cui 对数据库 server 的所有操作权限

## 数据库导入

完成环境配置之后登录 server 数据库并执行根目录下的 database.sql 文件导入数据

## 构建运行

使用 mvn package 命令构建项目，构建完成后启动数据库和 Mosquitto，之后使用 java -jar 命令运行构建的 jar 包，访问 http://localhost:8080 即可访问平台 web 界面。登录用户可为：

- 用户：cui，密码：123
- 用户：li，密码：123
- 用户：zhang，密码：123

