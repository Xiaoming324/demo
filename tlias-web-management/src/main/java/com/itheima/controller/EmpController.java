package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     */
    /*@GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize, String name, Integer gender, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询：{}，{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageResult);
    }*/
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询：{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 新增员工
     */
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) throws Exception {
        log.info("新增员工：{}", emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 查询全部员工
     */
    @GetMapping("/list")
    public Result list() {
        log.info("查询全部员工");
        List<Emp> empList = empService.findAll();
        return Result.success(empList);
    }

    /**
     * 删除员工
     */
    /*@DeleteMapping
    public Result delete(Integer[] ids){
        log.info("删除员工：{}", Arrays.toString(ids));
        return Result.success();
    }*/
    @Log
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 根据id查询员工信息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据id查询员工信息：{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 修改员工
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工：{}", emp);
        empService.update(emp);
        return Result.success();
    }

    /**
     * 修改员工密码
     */
    @Log
    @PutMapping("/password")
    public Result updatePassword(Integer id, String password) {
        log.info("修改id为{}的员工密码：{}", id, password);
        empService.updatePassword(id, password);
        return Result.success();
    }
}
