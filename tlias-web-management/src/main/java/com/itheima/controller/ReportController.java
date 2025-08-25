package com.itheima.controller;

import com.itheima.pojo.JobOption;
import com.itheima.pojo.NumOfStuOption;
import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    /**
     * 统计员工职位人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 统计员工性别人数
     */
    @GetMapping("empGenderData")
    public Result getEmpGenderData() {
        log.info("统计员工性别人数");
        List<Map<String, Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /**
     * 统计每一个班级的人数
     */
    @GetMapping("/studentCountData")
    public Result getStuCountData() {
        log.info("统计每一个班级的人数");
        NumOfStuOption numOfStuOption = reportService.getStuCountData();
        return Result.success(numOfStuOption);
    }

    /**
     * 统计学员的学历信息
     */
    @GetMapping("/studentDegreeData")
    public Result getStuDegreeData() {
        log.info("统计学员的学历信息");
        List<Map<String, Object>> list = reportService.getStuDegreeData();
        return Result.success(list);
    }

}
