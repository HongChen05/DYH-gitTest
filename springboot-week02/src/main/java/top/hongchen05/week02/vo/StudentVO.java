package top.hongchen05.week02.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hongchen05.week02.constant.GenderEnum;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentVO {
    private Long id;
    private String name;
    private String mobile;
    private GenderEnum gender;
    //格式化时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GHT+8")
    private LocalDateTime createTime;
}