package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    /**
     * 新增班级
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Clazz clazz) {
        log.info("添加班级：{}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    /**
     * 查询所有班级
     */
    @GetMapping("/list")
    public Result list() {
        log.info("查询所有班级");
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }

    /**
     * 查询班级列表
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("查询班级列表，{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 根据id删除班级
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据Id删除班级：{}", id);
        clazzService.deleteById(id);
        return Result.success();
    }

    /**
     * 根据id查询班级
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询部门：{}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级：{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }
}
