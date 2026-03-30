package top.hongchen05.week04.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hongchen05.week04.common.Result;
import top.hongchen05.week04.entity.User;
import java.time.LocalDateTime;

/**
 * @author hongchen05
 * @date 2026/3/26
 * @description UserController
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    /**
     * 获取用户信息，测试消息转换器
     */
    @GetMapping("/info")
    public Result<User> getUserInfo() {
        User user = new User();
        user.setId(1234567890123456789L);
        user.setUsername("springmvc-student");
        user.setCreateTime(LocalDateTime.now());
        return Result.success(user);
    }
}