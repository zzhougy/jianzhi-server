# 步骤1: 选择基础镜像
FROM maven:3.8.1-jdk-11-slim AS build
WORKDIR /app

# 步骤2: 复制项目文件并构建
COPY src ./src
COPY pom.xml .
RUN mvn -f pom.xml clean package

# 步骤3: 选择运行时基础镜像
FROM openjdk:11-jre-slim
WORKDIR /app

# 步骤4: 从构建阶段复制构建好的jar文件
COPY --from=build /app/target/*.jar ./app.jar

# 步骤5: 运行Spring Boot应用
CMD ["java","-jar","app.jar"]