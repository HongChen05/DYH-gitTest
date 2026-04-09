package top.hongchen05.week05.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.hongchen05.week05.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 无需写任何方法，BaseMapper自带insert
}