package top.hongchen05.tweek03.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BaseConfigController {

    // 从 server 节点获取端口（修正：app.port -> server.port）
    @Value("${server.port}")
    private String port;

    // 从 server 节点获取上下文路径（修正：app.contextPath -> server.servlet.context-path）
    @Value("${server.servlet.context-path}")
    private String contextPath;

    // 给不存在的配置项设置默认值，避免启动失败（修正：添加默认值 :""）
    @Value("${spring.application.name:}")
    private String applicationName;

    // 从 app 节点获取应用名称（修正：app.appName -> app.app-name）
    @Value("${app.app-name}")
    private String appName;

    // 以下配置项原本就存在，无需修改
    @Value("${app.version}")
    private String version;

    @Value("${app.description}")
    private String description;

    @Value("${app.published}")
    private Boolean published;

    /**
     * 获取基础配置信息接口
     */
    @GetMapping("/base")
    public Map<String, Object> getBaseConfig() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "success");

        Map<String, Object> data = new HashMap<>();
        data.put("port", port);
        data.put("contextPath", contextPath);
        data.put("applicationName", applicationName);
        data.put("appName", appName);
        data.put("version", version);
        data.put("description", description);
        data.put("published", published);

        result.put("data", data);
        return result;
    }
}