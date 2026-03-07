package top.hongchen05.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    public String getHello() {
        return "Hello SpringBoot!";
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

    @GetMapping("/info")
    public Map<String, String> info() {
        Map<String, String> info = new HashMap<>();
        info.put("application", "hello-sample");
        info.put("port", "8888");
        info.put("author", "HongChen05");
        return info;
    }
}