package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录Controller
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private EmpService empService;

    /**
     * 登录
     */
    @PostMapping
    public Result login(@RequestBody Emp emp) {
        log.info("登录：{}", emp);

        /*EmpLoginLog empLoginLog = new EmpLoginLog();
        empLoginLog.setUsername(emp.getUsername());
        empLoginLog.setPassword(emp.getPassword());*/

        LoginInfo info = empService.login(emp);
        if (info != null) {
            /*empLoginLog.setIsSuccess((short) 1);
            empLoginLog.setJwt(info.getToken());
            EmpLoginLogHolder.setIEmpLoginLog(empLoginLog);*/
            return Result.success(info);
        }

        /*empLoginLog.setIsSuccess((short) 0);
        EmpLoginLogHolder.setIEmpLoginLog(empLoginLog);*/
        return Result.error("用户名或密码错误");
    }
}
