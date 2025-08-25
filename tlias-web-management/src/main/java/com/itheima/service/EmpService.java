package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface EmpService {
    /**
     * 分页查询
     * @param page     页码
     * @param pageSize 每页展示记录数
     */
    // PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 分页查询
     *
     * @param empQueryParam
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     */

    void save(Emp emp) throws Exception;

    /**
     * 查询全部员工
     */
    List<Emp> findAll();

    /**
     * 批量删除员工
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询员工信息
     */
    Emp getInfo(Integer id);

    /**
     * 修改员工
     */
    void update(Emp emp);

    /**
     * 员工登录
     */
    LoginInfo login(Emp emp);

    /**
     * 修改员工密码
     */
    void updatePassword(Integer id, String password);
}
