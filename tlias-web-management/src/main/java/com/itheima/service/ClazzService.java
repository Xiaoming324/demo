package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    /**
     * 新增班级
     */
    void add(Clazz clazz);

    /**
     * 查询所有班级
     */
    List<Clazz> findAll();

    /**
     * 查询班级列表
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 根据Id删除班级
     */
    void deleteById(Integer id);

    /**
     * 根据Id查询班级
     */
    Clazz getById(Integer id);

    /**
     * 修改班级
     */
    void update(Clazz clazz);
}
