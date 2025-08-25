package com.itheima.service.impl;

import com.itheima.exception.DeleteDeptException;
import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        Integer empCount = deptMapper.getEmpCount(id);
        if (empCount == 0) {
            deptMapper.deleteById(id);
        } else {
            throw new DeleteDeptException("Cannot delete a dept if there are emps in it");
        }
    }

    @Override
    public void add(Dept dept) {
        // 1.补全基础属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        // 2.调用Mapper接口方法插入数据
        deptMapper.insert(dept);
    }

    /**
     * 根据ID查询部门数据
     */
    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    /**
     * 修改部门
     */
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }


}
