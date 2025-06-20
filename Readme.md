# 生成题库管理系统 API 文档

## 项目概述
生成题库管理系统是一个用于管理各类题目的后台服务，支持题目的创建、查询、更新、删除及批量操作等功能。本系统基于 Spring Boot 框架开发，使用 MySQL 数据库进行数据存储，为前端应用提供完整的 RESTful API 接口。



## 数据库设计


### 用户表(users)

| 字段名     | 类型          | 必填 | 说明                 | 示例值               |
|------------|---------------|------|----------------------|----------------------|
| id         | BIGINT     | 是   | 主键，自增           | 1                    |
| name       | VARCHAR(50)   | 是   | 用户名，唯一         | "alice"             |
| password   | VARCHAR(255)  | 是   | 密码（加密存储）     | （加密后的字符串）   |



### 题目表(questions)

| 字段名     | 类型                                                    | 必填 | 说明              | 示例值               |
| ---------- | ------------------------------------------------------- | ---- | ----------------- | -------------------- |
| id         | BIGINT                                                  | 是   | 主键，自增        | 1                    |
| type       | enum('SINGLE_CHOICE', 'MULTIPLE_CHOICE', 'PROGRAMMING') | 是   | 题目类型          | SINGLE_CHOICE        |
| content    | TEXT                                                    | 是   | 题目内容          | HTTP是无状态协议吗？ |
| options    | TEXT                                                    | 否   | 选择题选项        | `"A:是,B:否"`        |
| answer     | VARCHAR(255)                                            | 否   | 题目答案(选择题)  | A                    |
| difficulty | enum('EASY', 'MEDIUM', 'HARD')                          | 否   | 难度等级          | EASY                 |
| language   | VARCHAR(50)                                             | 否   | 编程语言/题目类别 | Java                 |
| created_at | DATETIME                                                | 是   | 创建时间          | 2025-06-15 10:30:00  |
| user_id    | BIGINT                                                  | 是   | 创建问题的用户    | 1                    |

### 枚举类型说明

**题目类型(QuestionType):**

- SINGLE_CHOICE: 单选题
- MULTIPLE_CHOICE: 多选题
- PROGRAMMING: 编程题

**难度等级(DifficultyLevel):**

- EASY: 容易
- MEDIUM: 中等
- HARD: 困难



## API 接口文档

### 基础信息
- **基础URL**: `/api`

- **默认响应格式**: JSON

- **认证方式**: 
  ```http
  Authorization: Bearer <token>
  ```

### 接口目录

#### 1. 创建题目
- **请求方法**: POST  
- **路径**: `/questions`  
- **请求体**:
```json
{
  "type": "SINGLE_CHOICE",
  "content": "HTTP是无状态协议",
  "options": "A: 真; B: 假",
  "answer": "A",
  "difficulty": "EASY",
  "language": "计算机基础",
  "userId":1
}
```

- **响应体（201 Created）**:

```json
{
    "success": true,
    "message": "操作成功",
    "data": {
        "id": 11,
        "type": "SINGLE_CHOICE",
        "content": "HTTP是无状态协议",
        "options": "A: 真; B: 假",
        "answer": "A",
        "difficulty": "EASY",
        "language": "计算机基础",
        "createdAt": "2025-06-18T11:20:34",
        "userId": 1
    }
}
```

#### 2. 更新题目
- **请求方法**: PUT
- **路径**: `/questions/{id}`  
- **路径参数**:id(题目ID)
- **请求体**:
```json
{
  "type": "SINGLE_CHOICE",
  "content": "HTTP是无状态协议",
  "options": "A: 真; B: 假",
  "answer": "A",
  "difficulty": "EASY",
  "language": "计算机基础",
  "userId":3
}
```

- **响应体（200 ok）**:

```json
{
    "success": true,
    "message": "操作成功",
    "data": {
        "id": 10,
        "type": "SINGLE_CHOICE",
        "content": "HTTP是无状态协议",
        "options": "A: 真; B: 假",
        "answer": "A",
        "difficulty": "EASY",
        "language": "计算机基础",
        "createdAt": "2025-06-18T11:13:34",
        "userId": 3
    }
}
```

#### 3. 获取题目信息（通过id）
- **请求方法**: GET 
- **路径**: `/questions/{id}`  
- **路径参数**:id(题目ID)
- **请求体**:
```json
无
```

- **响应体（200 ok）**:

```json
{
    "success": true,
    "message": "操作成功",
    "data": {
        "id": 10,
        "type": "SINGLE_CHOICE",
        "content": "HTTP是无状态协议",
        "options": "A: 真; B: 假",
        "answer": "A",
        "difficulty": "EASY",
        "language": "计算机基础",
        "createdAt": "2025-06-18T11:13:34",
        "userId": 3
    }
}
```

#### 4. （批量）删除题目
- **请求方法**: POST  
- **路径**: `/questions/batch-delete`  
- **请求体**:
```json
{
  "ids": [7, 8, 9]
}
```

- **响应体（200 ok）**:

```json
{
    "success": true,
    "message": "操作成功",
    "data": "批量删除成功"
}
```

#### 5. 分页查询题目
- **请求方法**: GET
- **路径**: `/questions`  
- **查询参数**:
  - `page`: 页码(默认0)
  - `size`: 每页数量(默认10)
  - `type`: 题目类型(可选)
  - `difficulty`: 难度等级(可选)
  - `language`: 编程语言/题目类别(可选)
  - `keyword`: 题目内容关键词(大小写模糊查询)(可选)
  - `userId`:创建者id
  - `sortBy`: 排序字段(默认createdAt)
  - `direction`: 排序方向(ASC/DESC, 默认DESC)
- **请求体**:
```json
无
```

- **响应体（200 ok）**:

```json
{
    "content": [
        {
            "id": 11,
            "type": "SINGLE_CHOICE",
            "content": "HTTP是无状态协议",
            "difficulty": "EASY",
            "options": "A: 真; B: 假",
            "createdAt": "2025-06-18T11:20:34",
            "language": "计算机基础",
            "userId": 1
        },
        {
            "id": 10,
            "type": "SINGLE_CHOICE",
            "content": "HTTP是无状态协议",
            "difficulty": "EASY",
            "options": "A: 真; B: 假",
            "createdAt": "2025-06-18T11:13:34",
            "language": "计算机基础",
            "userId": 3
        },
        {
            "id": 7,
            "type": "SINGLE_CHOICE",
            "content": "好",
            "difficulty": "EASY",
            "options": "A: 真; B: 假",
            "createdAt": "2025-06-14T21:45:57",
            "language": "计算机基础",
            "userId": 1
        },
        {
            "id": 6,
            "type": "SINGLE_CHOICE",
            "content": "https是无状态协议",
            "difficulty": "EASY",
            "options": "A: 真; B: 假",
            "createdAt": "2025-06-14T21:45:55",
            "language": "计算机基础",
            "userId": 1
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalElements": 4,
    "totalPages": 1,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 4,
    "empty": false
}
```

#### 6. 用户注册
- **请求方法**: POST
- **路径**: `/users/register`  
- **请求体**:
```json
{
  "name": "用户名",
  "password": "qwq"
}
```

- **响应体（200 ok）**:

```json
{
    "id": 4,
    "name": "用户名",
    "password": "$2a$10$cZjDIVgNYebUc6.3X838EO5NBKhs6tRqxNGSgcUdsnfbNGanIimXm"
}
```

#### 7. 用户登录
- **请求方法**: POST
- **路径**: `/users/login`  
- **请求体**:
```json
{
  "name": "用户名",
  "password": "qwq"
}
```

- **响应体（200 ok）**:

```json
{
    "id": 4,
    "name": "用户名",
    "password": "$2a$10$cZjDIVgNYebUc6.3X838EO5NBKhs6tRqxNGSgcUdsnfbNGanIimXm"
}
```

#### 8. 修改密码

- **请求方法**: POST
- **路径**: `/users/change-password`  
- **请求体**:

```json
{
  "username": "用户名1",
  "oldPassword": "qwq",
  "newPassword": "qwqwq"
}
```

- **响应体（200 ok）**:

```json
无
```

