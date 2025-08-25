package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    /**
     * 学员列表数据的条件分页查询
     */
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 添加学员信息
     */
    void save(Student student);

    /**
     * 根据主键ID查询学员的信息
     */
    Student getInfo(Integer id);

    /**
     * 修改学员的数据信息
     */
    void update(Student student);

    /**
     * 批量删除学员信息
     */
    void delete(List<Integer> ids);

    /**
     * 修改学员的违纪处理数据信息
     */
    void punish(Integer id, Integer score);

}
