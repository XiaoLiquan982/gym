# Gym Management System

## Overview

This repository contains a gym management system with:

- `frontend`: Vue 3 + Vite frontend
- `gym-management-system`: Spring Boot backend

The backend has been simplified to use MyBatis-Plus for common CRUD operations, reducing manual SQL and mapper XML maintenance.

## Backend Stack

- Java 17
- Spring Boot 3.5.11
- MyBatis-Plus 3.5.15
- MySQL 8

## Frontend Stack

- Vue 3
- Vite
- Element Plus

## Project Structure

```text
gym/
|- frontend/
|- gym-management-system/
`- gym_management_system.sql
```

## Backend Setup

Backend path:

```text
gym-management-system
```

Configure the database in:

```text
gym-management-system/src/main/resources/application.yml
```

Example:

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/gym_management_system
    username: root
    password: your password
    driver-class-name: com.mysql.cj.jdbc.Driver
```

Run the backend:

```bash
mvn spring-boot:run
```

## Frontend Setup

Frontend path:

```text
frontend
```

Install and run:

```bash
npm install
npm run dev
```

## Database Initialization

Use the SQL file in the repository root:

```text
gym_management_system.sql
```

Import it into MySQL before starting the backend.

## Notes

- The backend response format has been unified.
- Session-based authentication is currently used.
- Common single-table CRUD has been migrated to MyBatis-Plus.
- Some business-specific logic still remains in the service layer.
