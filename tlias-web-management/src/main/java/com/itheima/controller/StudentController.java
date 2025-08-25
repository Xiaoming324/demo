package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 学员列表数据的条件分页查询
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("学员列表数据的条件分页查询:{}", studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 添加学员信息
     */
    @Log
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("添加学员信息：{}", student);
        studentService.save(student);
        return Result.success();
    }

    /**
     * 根据主键ID查询学员的信息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据主键ID查询学员的信息：{}", id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    /**
     * 修改学员的数据信息
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员的数据信息：{}", student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 批量删除学员信息
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除学员信息：{}", ids);
        studentService.delete(ids);
        return Result.success();
    }

    /**
     * 修改学员的违纪处理数据信息
     */
    @PutMapping("/violation/{id}/{score}")
    public Result punish(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("违纪处理学员id和扣分：{}，{}",id,score);
        studentService.punish(id,score);
        return Result.success();
    }
}
