package top.hongchen05.week04.exception;

import lombok.Getter;

/**
 * 自定义业务异常类
 */
@Getter
public class BusinessException extends RuntimeException {
    private final int code;

    // 仅传入消息，默认错误码 500
    public BusinessException(String msg) {
        super(msg);
        this.code = 500;
    }

    // 传入错误码和消息
    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}