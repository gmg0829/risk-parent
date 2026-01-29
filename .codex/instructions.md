## 项目背景
这是一个银行风控相关的后端项目

## 技术栈
- Java 17
- Spring Boot
- Maven
- Oracle 12c / MySQL 8
- 日志使用 slf4j + logback

## 设计要求
- 分层清晰（controller / service / dao）
- 不允许在 SQL 中使用 delete without where
- 所有数据库操作必须可回滚
- 注重可读性，优先明确代码而非炫技

## 禁止事项
- 不要引入不必要的第三方框架
- 不要生成测试用的硬编码账号密码

