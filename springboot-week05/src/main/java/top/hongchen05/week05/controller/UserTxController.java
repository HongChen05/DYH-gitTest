package top.hongchen05.week05.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hongchen05.week05.common.Result;
import top.hongchen05.week05.entity.User;
import top.hongchen05.week05.service.UserTxService;

@RestController
@RequestMapping("/api/user/tx")
@RequiredArgsConstructor
@Tag(name = "事务测试接口", description = "验证@Transactional事务回滚")
public class UserTxController {
    private final UserTxService userTxService;

    @Operation(summary = "新增两个用户（事务测试）")
    @PostMapping("/addTwo")
    public Result<String> addTwo(@RequestBody UserTwoParam param) {
        userTxService.addTwoUsers(param.getUser1(), param.getUser2());
        return Result.success("两个用户新增成功，事务正常提交");
    }

    // 新建参数包装类（内部类，无需单独文件）
    @lombok.Data
    public static class UserTwoParam {
        private User user1;
        private User user2;
    }
}