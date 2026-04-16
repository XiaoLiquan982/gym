# 健身房管理系统

## 项目简介

这是一个前后端分离的健身房管理系统，包含：

- `frontend`：基于 Vue 3 的前端项目
- `gym-management-system`：基于 Spring Boot 3 的后端项目

系统主要用于健身房的会员、员工、器械、课程与选课管理。

## AI 功能说明

系统集成了 AI 对话功能，会员登录后可以在前端使用智能助手进行交流。

当前 AI 功能包括：

- 基于聊天接口进行自然语言问答
- 结合当前会员已报名课程生成更贴合业务场景的回复
- 可用于课程咨询、训练建议、健身房使用场景下的辅助问答

示例配置：

```yml
deepseek:
  api:
    key: your-deepseek-api-key
    url: https://api.deepseek.com/v1/chat/completions
  model: deepseek-chat
```

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


数据库配置文件：

```text
gym-management-system/src/main/resources/application.yml
```

启动命令：

```bash
mvn spring-boot:run
```

## 前端启动

启动命令：

```bash
npm install
npm run dev
```
## 界面展示
<img width="1429" height="1101" alt="image" src="https://github.com/user-attachments/assets/1165c894-5566-4230-baa9-7961b014c8ff" />

<img width="1483" height="1075" alt="image" src="https://github.com/user-attachments/assets/91f3d9a2-43d5-455d-b0f5-990a45fd432e" />

