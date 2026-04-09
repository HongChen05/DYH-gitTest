package top.hongchen05.week05;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("top.hongchen05.week05.mapper") // 扫描Mapper接口
@EnableTransactionManagement // 开启事务管理
public class SpringbootWeek05Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWeek05Application.class, args);
    }
}