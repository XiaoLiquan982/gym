# 健身房管理系统

## 项目简介

这是一个前后端分离的健身房管理系统，包含：

- `frontend`：基于 Vue 3 + Vite 的前端项目
- `gym-management-system`：基于 Spring Boot 的后端项目

当前后端已经完成两项简化工作：

- 统一了接口返回结构
- 将大部分单表增删改查迁移到了 MyBatis-Plus，删除了原有的大量 Mapper XML

## 技术栈

### 前端

- Vue 3
- Vite
- Element Plus
- Axios

### 后端

- Java 17
- Spring Boot 3.5.11
- MyBatis-Plus 3.5.15
- MySQL 8

## 项目结构

```text
gym/
|- frontend/
|- gym-management-system/
`- gym_management_system.sql
```

## 数据库初始化

数据库脚本位于项目根目录：

```text
gym_management_system.sql
```

启动后端前，请先在 MySQL 中创建并导入该脚本。

## 后端启动

后端目录：

```text
gym-management-system
```

数据库配置文件：

```text
gym-management-system/src/main/resources/application.yml
```

配置示例：

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/gym_management_system
    username: root
    password: your password
    driver-class-name: com.mysql.cj.jdbc.Driver
```

启动命令：

```bash
mvn spring-boot:run
```

## 前端启动

前端目录：

```text
frontend
```

启动命令：

```bash
npm install
npm run dev
```

## 当前说明

- 后端接口已统一返回 `success`、`code`、`message` 等字段
- 认证方式目前为基于 Session 的登录态校验
- 常规单表 CRUD 已迁移到 MyBatis-Plus
- 少量业务逻辑仍保留在 Service 层中
- 项目中仍存在部分历史中文乱码，后续建议继续清理

## 后续可优化方向

- 修复历史中文编码乱码问题
- 增加统一异常码说明
- 为前后端补充更完整的接口文档
- 增加测试用例和部署说明
