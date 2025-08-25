package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.exception.DeleteClazzException;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
        /*// 判断状态
        determinStatus(clazzList);*/
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<Clazz>(p.getTotal(), p.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        Integer count = clazzMapper.getStudentCount(id);
        if (count == 0){
            clazzMapper.deleteById(id);
        } else {
            throw new DeleteClazzException("Cannot delete a class if there are students in it");
        }
    }

    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

   /* private void determinStatus(List<Clazz> clazzList) {
        if (!CollectionUtils.isEmpty(clazzList)) {
            LocalDate now = LocalDate.now();
            clazzList.forEach(clazz -> {
                if (now.isBefore(clazz.getBeginDate())) {
                    clazz.setStatus("未开课");
                } else if (now.isAfter(clazz.getEndDate())) {
                    clazz.setStatus("已结课");
                } else {
                    clazz.setStatus("在读");
                }
            });
        }
    }*/
}
