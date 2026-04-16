# 健身房管理系统

## 项目简介

这是一个前后端分离的健身房管理系统，包含：

- `frontend`：基于 Vue 3 的前端项目
- `gym-management-system`：基于 Spring Boot 3 的后端项目



## 技术栈

### 前端

- Vue 3
- Element Plus

### 后端

- Java 17
- Spring Boot 3.5.11
- MyBatis-Plus 3.5.15
- MySQL 8

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
