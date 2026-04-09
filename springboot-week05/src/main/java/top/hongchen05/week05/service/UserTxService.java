package top.hongchen05.week05.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.hongchen05.week05.entity.User;
import top.hongchen05.week05.mapper.UserMapper;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional // 类级别事务，所有方法生效
public class UserTxService {
    private final UserMapper userMapper;

    /**
     * 新增两个用户，事务控制
     */
    public void addTwoUsers(User user1, User user2) {
        // 1. 插入用户1
        user1.setCreateTime(LocalDateTime.now());
        // 用MyBatis-Plus的insert方法（如果是原生MyBatis，方法名对应即可）
        userMapper.insert(user1);

        // 2. 校验用户2，空则抛异常
        if (user2.getUsername() == null || user2.getUsername().trim().isEmpty()) {
            throw new RuntimeException("用户2姓名不能为空，事务回滚！");
        }

        // 3. 插入用户2
        user2.setCreateTime(LocalDateTime.now());
        userMapper.insert(user2);
    }
}