package com.itheima.mapper;

import com.itheima.pojo.EmpLoginLog;
import com.itheima.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    //插入日志数据
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    public void insert(OperateLog log);

    /**
     * 日志信息的查询
     */
    @Select("select o.*, e.name operateEmpName from operate_log o, emp e where o.operate_emp_id = e.id order by o.operate_time desc ")
    List<OperateLog> list();

    /**
     * 记录员工登录日志
     */
    @Insert("insert into emp_login_log(username, password, login_time, is_success, jwt, cost_time) values(#{username}, #{password}, #{loginTime}, #{isSuccess}, #{jwt}, #{costTime})")
    public void insertLoginLog(EmpLoginLog empLoginLog);

}
