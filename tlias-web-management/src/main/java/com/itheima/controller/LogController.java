package com.itheima.controller;

import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 日志信息的分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("日志信息的分页查询:page{}，pageSize{}",page,pageSize);
        PageResult<OperateLog> pageResult = logService.page(page, pageSize);
        return Result.success(pageResult);
    }
}
