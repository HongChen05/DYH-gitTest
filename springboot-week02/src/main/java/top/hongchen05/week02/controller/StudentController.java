package top.hongchen05.week02.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.hongchen05.week02.dto.StudentAddDTO;
import top.hongchen05.week02.dto.StudentUpdateDTO;
import top.hongchen05.week02.entity.Student;
import top.hongchen05.week02.service.StudentService;
import top.hongchen05.week02.vo.StudentVO;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Resource
    private StudentService studentService;

    @GetMapping("/all")
    public Collection<StudentVO> getAllStudents(){
        return studentService.getAllStudents();
    }

    /**
     * 根据id获取学生
     * @param id 学生ID
     * @return 学生VO数据
     */
    @GetMapping("/{id}")
    public StudentVO getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    /**
     * 根据名称模糊查询学生
     * @param name 学生名称
     * @return 学生VO数据
     */
    @GetMapping()
    public List<StudentVO> getStudentByName(@RequestParam String name) {
        return studentService.getStudentByName(name);
    }

    /**
     * 添加学生
     * @param studentAddDTO 新增学生的DTO数据
     */
    @PostMapping
    public void addStudent(@RequestBody StudentAddDTO studentAddDTO) {
        studentService.addStudent(studentAddDTO);
    }

    /**
     * 修改学生
     * @param id 学生ID
     * @param studentUpdateDTO 修改的学生DTO数据
     */
    @PutMapping("/{id}")
    public String updateStudent(@PathVariable Long id, @RequestBody StudentUpdateDTO studentUpdateDTO) {
        studentService.updateStudent(id, studentUpdateDTO);
        return "成功";
    }

    /**
     * 删除学生
     * @param id 学生ID
     */
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "成功";
    }

}