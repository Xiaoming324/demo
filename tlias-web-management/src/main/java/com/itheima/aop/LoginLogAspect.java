package com.itheima.aop;

import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.EmpLoginLog;
import com.itheima.utils.EmpLoginLogHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Aspect
@Component
@Slf4j
public class LoginLogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Pointcut("execution(public com.itheima.pojo.Result com.itheima.controller.LoginController.login(com.itheima.pojo.Emp))")
    private void pt() {
    }

    @Around("pt()")
    public Object loginLogOperation(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;

        EmpLoginLog empLoginLog = EmpLoginLogHolder.getEmpLoginLog();
        empLoginLog.setLoginTime(LocalDateTime.now());
        empLoginLog.setCostTime(costTime);

        log.info("记录登录日志：{}", empLoginLog);
        operateLogMapper.insertLoginLog(empLoginLog);
        EmpLoginLogHolder.remove();

        return result;
    }
}