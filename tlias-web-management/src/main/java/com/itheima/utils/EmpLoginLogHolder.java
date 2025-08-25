package com.itheima.utils;

import com.itheima.pojo.EmpLoginLog;

public class EmpLoginLogHolder {

    private static final ThreadLocal<EmpLoginLog> CURRENT_LOCAL = new ThreadLocal<>();

    public static void setIEmpLoginLog(EmpLoginLog empLoginLog) {
        CURRENT_LOCAL.set(empLoginLog);
    }

    public static EmpLoginLog getEmpLoginLog() {
        return CURRENT_LOCAL.get();
    }

    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}
