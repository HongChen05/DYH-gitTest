package top.hongchen05.week02.service;

import org.springframework.stereotype.Service;
import top.hongchen05.week02.constant.GenderEnum;
import top.hongchen05.week02.dto.StudentAddDTO;
import top.hongchen05.week02.dto.StudentUpdateDTO;
import top.hongchen05.week02.entity.Student;
import top.hongchen05.week02.vo.StudentVO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StudentService {
    private static final Map<Long, Student> STUDENT_DATA = new ConcurrentHashMap<>();

    // 静态代码块初始化测试数据
    static {
        Student student1 = Student.builder()
                .id(1001L)
                .name("张三")
                .mobile("13888888888")
                .gender(GenderEnum.MALE)
                .avatar("http:")
                .enabled(true)
                .birthday(LocalDate.of(1990, 1, 1))
                .createTime(LocalDateTime.now())
                .build();
        Student student2 = Student.builder()
                .id(1002L)
                .name("张三丰")
                .mobile("12345678901")
                .gender(GenderEnum.FEMALE)
                .avatar("http:")
                .enabled(true)
                .birthday(LocalDate.of(1990, 1, 1))
                .createTime(LocalDateTime.now())
                .build();
        STUDENT_DATA.put(student1.getId(), student1);
        STUDENT_DATA.put(student2.getId(), student2);
    }

    /**
     * 获取所有学生
     * @return 所有学生数据
     */
    public List<StudentVO> getAllStudents(){
        List<StudentVO> list = new ArrayList<>();
        STUDENT_DATA.values().forEach(student -> list.add(StudentVO.builder()
                .id(student.getId())
                .name(student.getName())
                .mobile(student.getMobile())
                .gender(student.getGender())
                .createTime(student.getCreateTime())
                .build()));
        return  list;
    }

    /**
     * 添加学生
     *
     * @param studentAddDTO 新增学生的DTO数据
     */
    public void addStudent(StudentAddDTO studentAddDTO) {
        Student student = Student.builder()
                // id自增，这里使用系统时间
                .id(System.currentTimeMillis())
                .name(studentAddDTO.getName())
                .mobile(studentAddDTO.getMobile())
                .gender(studentAddDTO.getGender())
                .avatar(studentAddDTO.getAvatar())
                .enabled(true)
                .birthday(studentAddDTO.getBirthday())
                .createTime(LocalDateTime.now())
                .build();
        STUDENT_DATA.put(student.getId(), student);
    }

    /**
     * 根据id获取学生
     * @param id 学生ID
     * @return 学生VO数据
     */
    public StudentVO getStudent(Long id) {
        Student student = STUDENT_DATA.get(id);
        if (student == null) {
            throw new RuntimeException("学生ID不存在：" + id);
        }
        return StudentVO.builder()
                .id(student.getId())
                .name(student.getName())
                .mobile(student.getMobile())
                .gender(student.getGender())
                .createTime(student.getCreateTime())
                .build();
    }

    /**
     * 根据名称模糊查询学生
     * @param name 学生名称
     * @return 学生VO数据
     */
    public List<StudentVO> getStudentByName(String name) {
        List<StudentVO> list = new ArrayList<>();
        STUDENT_DATA.values().forEach(student -> {
            if (student.getName().contains(name)) {
                list.add(StudentVO.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .mobile(student.getMobile())
                        .gender(student.getGender())
                        .createTime(student.getCreateTime())
                        .build());
            }
        });
        return list;
    }

    /**
     * 修改学生
     * @param id 学生ID
     * @param studentUpdateDTO 修改的学生DTO数据
     */
    public void updateStudent(Long id, StudentUpdateDTO studentUpdateDTO) {
        Student student = STUDENT_DATA.get(id);
        // 新增空指针校验，和getStudent保持一致
        if (student == null) {
            throw new RuntimeException("学生ID不存在：" + id);
        }
        // 优化：非null才更新，避免覆盖原有值为null
        if (studentUpdateDTO.getName() != null) {
            student.setName(studentUpdateDTO.getName());
        }
        if (studentUpdateDTO.getMobile() != null) {
            student.setMobile(studentUpdateDTO.getMobile());
        }
        if (studentUpdateDTO.getAvatar() != null) {
            student.setAvatar(studentUpdateDTO.getAvatar());
        }
    }

    /**
     * 删除学生
     * @param id 学生ID
     */
    public void deleteStudent(Long id) {
        // 可选：删除前校验，保持异常逻辑统一
        if (!STUDENT_DATA.containsKey(id)) {
            throw new RuntimeException("学生ID不存在：" + id);
        }
        STUDENT_DATA.remove(id);
    }
}