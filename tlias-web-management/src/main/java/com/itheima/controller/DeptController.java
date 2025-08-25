package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    // private static final Logger log = LoggerFactory.getLogger(DeptController.class); // 固定的
    @Autowired
    private DeptService deptService;

    /**
     * 查询部门列表
     */
    // @RequestMapping(value = "/depts", method = RequestMethod.GET) // method：指定请求方式
    @GetMapping
    public Result list() {
        // System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 删除部门 - 方式一：HttpServletRequest 获取请求参数
     */
    /*@DeleteMapping
    public Result delete(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        System.out.println("根据ID删除部门：" + id);
        return Result.success();
    }*/

    /**
     * 删除部门 - 方式二：@RequestParam
     * 注意事项：一旦声明了@RequestParam，该参数在请求时必须传递，如果不传递将会报错。（默认 required 为 true）
     */
    /*@DeleteMapping
    public Result delete(@RequestParam(value = "id", required = false) Integer deptId) {
        System.out.println("根据ID删除部门：" + deptId);
        return Result.success();
    }*/

    /**
     * 删除部门 - 方式三：省略@RequestParam（前端传递的请求参数名与服务端方法形参名一致）[推荐]
     */
    @Log
    @DeleteMapping
    public Result delete(Integer id) {
        // System.out.println("根据ID删除部门：" + id);
        log.info("根据ID删除部门：{}", id); // 可以避免字符串的拼接+
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        // System.out.println("添加部门：" + dept);
        log.info("添加部门：{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据ID查询部门
     */
    /*@GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer deptId){
        System.out.println("根据ID查询部门：" + deptId);
        return Result.success();
    }*/

    /**
     * 根据ID查询部门
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        // System.out.println("根据ID查询部门：" + id);
        log.info("根据ID查询部门：{}", id);
        Dept dept = deptService.getById(id); // id是主键，最多只会查询到一个数据
        return Result.success(dept);
    }

    /**
     * 修改部门
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        // System.out.println("修改部门" + dept);
        log.info("修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
