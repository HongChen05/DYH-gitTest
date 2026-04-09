package top.hongchen05.week05.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.hongchen05.week05.entity.User;
import top.hongchen05.week05.mapper.UserMapper;
import top.hongchen05.week05.service.UserService;

import java.util.List;

@Service
@Transactional // 🔥 类级别的事务注解：所有方法（增删改）都受事务控制
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<User> search(String username, Integer minAge, Integer maxAge, String email) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        if (username != null && !username.isEmpty()) {
            wrapper.like(User::getUsername, username);
        }
        if (minAge != null) {
            wrapper.ge(User::getAge, minAge);
        }
        if (maxAge != null) {
            wrapper.le(User::getAge, maxAge);
        }
        if (email != null && !email.isEmpty()) {
            wrapper.like(User::getEmail, email);
        }

        return list(wrapper);
    }

    @Override
    public Page<User> pageList(long current, long size) {
        Page<User> page = new Page<>(current, size);
        return page(page);
    }
}