package top.hongchen05.week02.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hongchen05.week02.constant.GenderEnum;
import top.hongchen05.week02.dto.StudentAddDTO;
import top.hongchen05.week02.dto.StudentUpdateDTO;
import top.hongchen05.week02.vo.StudentVO;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@SpringBootTest
// 指定测试方法按@Order注解顺序执行
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    // 1. 先查所有学生（验证初始化数据）
    @Test
    @Order(1)
    void getAllStudents() {
        List<StudentVO> allStudents = studentService.getAllStudents();
        allStudents.forEach(studentVO -> log.info("{}", studentVO));
    }

    // 2. 按ID查学生（1001L，确保存在）
    @Test
    @Order(2)
    void getStudent() {
        StudentVO studentVO = studentService.getStudent(1001L);
        log.info("{}", studentVO);
    }

    // 3. 按名称模糊查询（查"张"，能匹配张三、张三丰）
    @Test
    @Order(3)
    void getStudentByName() {
        List<StudentVO> studentVO = studentService.getStudentByName("张");
        studentVO.forEach(studentVO1 -> log.info("{}", studentVO1));
    }

    // 4. 添加学生（新增数据，不影响原有ID）
    @Test
    @Order(4)
    void addStudent() {
        StudentAddDTO addDTO = StudentAddDTO.builder()
                .name("李四")
                .mobile("13999999999")
                .gender(GenderEnum.MALE)
                .avatar("http://test.jpg")
                .birthday(LocalDate.of(1995, 5, 5))
                .build();
        studentService.addStudent(addDTO);
        log.info("添加成功");
    }

    // 5. 修改学生（修改1001L，确保ID存在）
    @Test
    @Order(5)
    void updateStudent() {
        studentService.updateStudent(1001L, StudentUpdateDTO.builder()
                .name("张三111")
                .mobile("12345678901")
                .avatar("https://mqxu.top/new.jpg")
                .build());
        log.info("修改成功");
        StudentVO studentVO = studentService.getStudent(1001L);
        log.info("修改后数据：{}", studentVO);
    }

    // 6. 最后删除学生（删除1002L，避免影响其他测试）
    @Test
    @Order(6)
    void deleteStudent() {
        studentService.deleteStudent(1002L);
        log.info("删除成功");
    }
}