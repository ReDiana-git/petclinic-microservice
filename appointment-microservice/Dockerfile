FROM gradle:jdk17 as build

# 設置容器內工作目錄
WORKDIR /app

# 複製 Gradle 配置文件
COPY build.gradle settings.gradle ./

# 複製源代碼
COPY src src

# 使用 Gradle 打包應用，跳過測試
RUN gradle build -x test

# 指定容器啟動時運行 Java 應用
ENTRYPOINT ["java","-jar","./build/libs/appointment-microservice-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080