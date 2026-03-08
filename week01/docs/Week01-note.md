week01 学习笔记
一、项目创建与基础配置
项目初始化
使用 IDEA 的 Spring Initializr 创建项目，核心配置：
名称：hello-sample
类型：Maven
Java 版本：17
打包方式：Jar
配置文件：.yml（或 .properties）
必选依赖：Spring Web（实现 Web 接口的核心）。
项目结构
plaintext
DYH-gitTest/
├── week01/
│   ├── hello-sample/          # SpringBoot 项目
│   │   ├── src/main/java/top/hongchen05/hello/
│   │   │   ├── controller/    # 控制层，存放接口类
│   │   │   └── HelloSampleApplication.java  # 项目启动类
│   │   ├── src/main/resources/
│   │   │   └── application.yml # 配置文件
│   │   └── pom.xml            # Maven 依赖配置
│   ├── docs/                  # 课程文档、笔记
│   └── screenshots/           # 运行截图、环境截图
端口配置
在 application.yml 中修改端口（注意 YAML 语法）：
yaml
server:
  port: 8888  # 自定义端口，避免 8080 被占用
注意：YAML 依赖缩进和空格，port: 后必须加空格，子项需缩进 2 个空格。

二、核心代码编写
启动类（HelloSampleApplication.java）
项目入口，由 @SpringBootApplication 注解标记：
java
运行
package top.hongchen05.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloSampleApplication.class, args);
    }
}
Controller 层（HelloController.java）
接口万能模板：
java
运行
@GetMapping("/接口地址")
public 返回值类型 方法名() {
    return 返回内容;
}
基础版任务（Hello 接口）：
java
运行
@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    public String getHello() {
        return "Hello SpringBoot!";
    }
}
挑战版任务（健康检查 + 系统信息）：
java
运行
// 1. 健康检查接口
@GetMapping("/health")
public String health() {
    return "ok";
}

// 2. 系统信息接口（返回 JSON）
@GetMapping("/info")
public Map<String, String> info() {
    Map<String, String> info = new HashMap<>();
    info.put("application", "hello-sample");
    info.put("port", "8888");
    info.put("author", "HongChen05");
    return info;
}

三、Maven 与依赖管理
pom.xml 核心作用
管理项目依赖（自动下载第三方库）。
定义项目构建规则（编译、打包等）。
示例（干净版，仅含 Web 依赖）：
xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- 挑战版任务需添加 Actuator 依赖 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
</dependencies>
依赖报错处理
红色波浪线：Maven 未下载依赖，点击 IDEA 右侧 Maven 面板的「刷新」按钮，或执行 mvn clean install -U 强制更新。
非必要依赖（如 Lombok）：可直接从 pom.xml 中删除，避免冗余。

四、运行与验证
启动项目
右键启动类 → 「Run」，控制台出现 Tomcat started on port(s): 8888 表示启动成功。
接口访问
Hello 接口：http://localhost:8888/api/hello
健康检查：http://localhost:8888/api/health
系统信息：http://localhost:8888/api/info

五、作业提交规范
目录结构：week01/ 下包含 hello-sample/、docs/、screenshots/。
附件：上传项目运行截图、环境配置截图。
代码：确保所有接口可正常访问，无编译错误。










































