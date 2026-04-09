package top.hongchen05.week05.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.hongchen05.week05.entity.User;
import java.util.List;

public interface UserService extends IService<User> {

    // 多条件模糊查询
    List<User> search(String username, Integer minAge, Integer maxAge, String email);

    // 分页查询
    Page<User> pageList(long current, long size);
}