package top.hongchen05.week02.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hongchen05.week02.constant.GenderEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long id;
    private String name;
    private String mobile;
    private GenderEnum gender;
    private String avatar;
    private Boolean enabled;
    private LocalDate birthday;
    private LocalDateTime createTime;
}