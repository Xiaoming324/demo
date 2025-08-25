package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("程序出错啦~", e);
        return Result.error("出错啦，请联系管理员~");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("程序出错啦~", e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2] + " 已存在");
    }

    @ExceptionHandler
    public Result handleDeleteClazzException(DeleteClazzException e) {
        log.error("程序出错啦~", e);
        return Result.error("对不起, 该班级下有学生, 不能直接删除");
    }

    @ExceptionHandler
    public Result handleDeleteDeptException(DeleteDeptException e) {
        log.error("程序出错啦~", e);
        return Result.error("对不起，当前部门下有员工，不能直接删除");
    }
}


