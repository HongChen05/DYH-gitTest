package top.hongchen05.week04.handler;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.hongchen05.week04.common.Result;
import top.hongchen05.week04.exception.BusinessException;

import java.util.StringJoiner;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleValidException(MethodArgumentNotValidException e) {
        StringJoiner sj = new StringJoiner("; ");
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            sj.add(fieldError.getDefaultMessage());
        }
        return Result.error(400, sj.toString());
    }

    /**
     * 处理自定义业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理其他所有异常
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        return Result.error(500, "服务器异常，请稍后重试");
    }
}