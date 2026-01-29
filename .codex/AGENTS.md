# Repository Guidelines

## Project Structure & Module Organization

```
risk-parent/
├── pom.xml                    # 父模块配置
├── risk-api/                  # API层 - DTOs、控制器
│   └── src/main/java/com/risk/api/
├── risk-service/              # 服务层 - 业务逻辑
│   └── src/main/java/com/risk/service/
├── risk-dao/                  # 数据访问层 - 实体和仓库
│   └── src/main/java/com/risk/dao/
└── risk-boot/                 # 启动模块
    └── src/main/java/com/risk/boot/
```

## Build, Test, and Development Commands

```bash
# 构建整个项目
mvn clean install

# 运行特定模块
mvn -pl risk-boot spring-boot:run

# 运行测试
mvn test

# 跳过测试构建
mvn clean install -DskipTests
```

## Coding Style & Naming Conventions

- **缩进**: 使用 4 空格
- **命名**: 
  - 类名: PascalCase (如 `RiskController`)
  - 方法名/变量: camelCase (如 `calculateRisk`)
  - 常量: UPPER_SNAKE_CASE
- **包名**: 全小写 (如 `com.risk.api`)
- **遵循**: Google Java Style Guide

## Testing Guidelines

- **框架**: JUnit 5 + Spring Boot Test
- **命名**: `*Test.java` 或 `*IT.java` (集成测试)
- **运行**: `mvn test`
- **覆盖**: 核心业务逻辑应达到 80%+ 覆盖率

## Commit & Pull Request Guidelines

- **提交信息**: 使用 Conventional Commits 格式
  ```
  feat: 添加风险评估接口
  fix: 修复数据验证bug
  docs: 更新README
  ```
- **PR要求**:
  - 描述清晰，链接相关 issue
  - 包含测试用例
  - 通过所有 CI 检查
  - 至少 1 人 Code Review

## Security & Configuration

- **敏感信息**: 使用环境变量或配置中心
- **本地配置**: `application-local.yml` (不提交到版本控制)
- **密钥管理**: 绝不硬编码密码或密钥
