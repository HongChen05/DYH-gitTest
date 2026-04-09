package top.hongchen05.week05.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    // 带数据+自定义消息
    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(200, msg, data);
    }

    // 仅带数据
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    // 无数据
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }

    // 失败
    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null);
    }
}