# Risk Management System

Spring Boot 多模块风险管理系统

## 项目结构

```
risk-parent/
├── pom.xml                    # 父模块
├── risk-api/                  # API层 - DTOs、控制器
├── risk-service/              # 服务层 - 业务逻辑
├── risk-dao/                  # 数据访问层
└── risk-boot/                 # 启动模块
```

## 快速启动

```bash
# 1. 编译项目
mvn clean install

# 2. 运行应用
mvn -pl risk-boot spring-boot:run

# 3. 访问健康检查
curl http://localhost:8080/actuator/health
```

## 配置说明

配置文件: `risk-boot/src/main/resources/application.yml`

```yaml
spring:
  datasource:
    mysql:
      url: jdbc:mysql://localhost:3306/risk_mysql
    oracle:
      url: jdbc:oracle:thin:@localhost:1521:risk_oracle
```

## 模块说明

| 模块 | 说明 |
|------|------|
| risk-api | API层，包含DTO和控制器 |
| risk-service | 服务层，业务逻辑实现 |
| risk-dao | 数据访问层，实体和Repository |
| risk-boot | 启动模块，包含主类和配置 |

## 数据对账模块

数据对账模块支持 MySQL → Oracle 数据对账：

```bash
# 按主键分页对账
POST /api/reconciliation/compare

# 输出差异ID
# 记录对账日志
```

## 技术栈

- Spring Boot 3.2.0
- Java 17
- Maven
- MySQL / Oracle
