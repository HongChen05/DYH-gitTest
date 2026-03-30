package top.hongchen05.week04.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.hongchen05.week04.common.Result;
import top.hongchen05.week04.entity.Team;
import top.hongchen05.week04.exception.BusinessException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/team")
@Slf4j
public class TeamController {

    @PostMapping("/add")
    public Result<String> addTeam(@Validated @RequestBody Team team, HttpServletRequest request) {
        log.info("添加团队：{}", team);

        // 模拟 Token 校验
        String token = request.getHeader("token");
        if (token == null || token.isBlank()) {
            throw new BusinessException(401, "请先登录");
        }
        if (!"admin".equals(token)) {
            throw new BusinessException(403, "没有权限");
        }

        // 模拟服务器异常
        // int a = 1 / 0;

        return Result.success("添加成功");
    }
}