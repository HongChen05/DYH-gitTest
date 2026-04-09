package top.hongchen05.week05.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hongchen05.week05.common.Result;
import top.hongchen05.week05.entity.User;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/test")
@Tag(name = "测试接口", description = "项目连通性测试、Swagger文档测试")
public class TestController {

    @Operation(summary = "项目连通性测试", description = "返回成功信息，验证项目启动正常")
    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("项目启动成功！TestController 运行正常 ✅");
    }

    @Operation(summary = "获取测试用户", description = "返回一个模拟的User实体，用于测试Swagger文档")
    @GetMapping("/user")
    public Result<User> getUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("123456");
        user.setAge(18);
        user.setEmail("test@qq.com");
        user.setCreateTime(LocalDateTime.now());
        return Result.success(user);
    }
}