package com.itheima.aop;

import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpLoginLog;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class LoginLogAspect2 {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Pointcut("execution(public com.itheima.pojo.Result com.itheima.controller.LoginController.login(com.itheima.pojo.Emp))")
    private void pt() {
    }

    @Around("pt()")
    public Object loginLogOperation(ProceedingJoinPoint pjp) throws Throwable {
        EmpLoginLog empLoginLog = new EmpLoginLog();
        Object result = null;
        try {
            long startTime = System.currentTimeMillis();
            result = pjp.proceed();
            long endTime = System.currentTimeMillis();
            long costTime = endTime - startTime;

            Emp emp = (Emp) pjp.getArgs()[0];
            empLoginLog.setUsername(emp.getUsername());
            empLoginLog.setPassword(emp.getPassword());

            empLoginLog.setLoginTime(LocalDateTime.now());

            Result loginResult = (Result) result;
            // empLoginLog.setIsSuccess(Short.valueOf(loginResult.getCode().toString()));
            empLoginLog.setIsSuccess(loginResult.getCode().shortValue());

            LoginInfo loginInfo = (LoginInfo) loginResult.getData();
            if (loginInfo != null) {
                empLoginLog.setJwt(loginInfo.getToken());
            }

            empLoginLog.setCostTime(costTime);

            log.info("记录登录日志：{}", empLoginLog);
        } catch (Exception e) {
            empLoginLog.setIsSuccess((short) 0);
            e.printStackTrace();
        } finally {
            operateLogMapper.insertLoginLog(empLoginLog);
        }
        return result;
    }
}