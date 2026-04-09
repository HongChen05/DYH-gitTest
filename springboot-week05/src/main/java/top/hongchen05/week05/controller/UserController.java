package top.hongchen05.week05.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.hongchen05.week05.common.Result;
import top.hongchen05.week05.entity.User;
import top.hongchen05.week05.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "用户管理接口", description = "用户CRUD、多条件查询、分页查询")
public class UserController {

    private final UserService userService;

    @Operation(summary = "查询所有用户", description = "返回全部用户列表，无参数")
    @GetMapping("/list")
    public Result<List<User>> list() {
        return Result.success(userService.list());
    }

    @Operation(summary = "根据ID查询用户", description = "路径参数传入用户ID，返回单个用户信息")
    @GetMapping("/{id}")
    public Result<User> get(@PathVariable Long id) {
        return Result.success(userService.getById(id));
    }

    @Operation(summary = "新增用户", description = "传入User实体，自动生成主键，返回成功信息")
    @PostMapping
    public Result<String> add(@RequestBody User user) {
        userService.save(user);
        return Result.success("添加成功");
    }

    @Operation(summary = "更新用户", description = "传入完整User实体，根据ID更新，返回成功信息")
    @PutMapping
    public Result<String> update(@RequestBody User user) {
        userService.updateById(user);
        return Result.success("修改成功");
    }

    @Operation(summary = "删除用户", description = "路径参数传入用户ID，删除对应记录，返回成功信息")
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        userService.removeById(id);
        return Result.success("删除成功");
    }

    @Operation(summary = "多条件模糊查询", description = "用户名/年龄区间/邮箱任意组合查询，参数可选")
    @GetMapping("/search")
    public Result<List<User>> search(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) String email
    ) {
        return Result.success(userService.search(username, minAge, maxAge, email));
    }

    @Operation(summary = "分页查询用户", description = "传入当前页和每页条数，返回分页数据")
    @GetMapping("/page")
    public Result<Page<User>> page(
            @RequestParam(defaultValue = "1") long current,
            @RequestParam(defaultValue = "10") long size
    ) {
        return Result.success(userService.pageList(current, size));
    }
}