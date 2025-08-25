package com.itheima.service;

import com.itheima.pojo.JobOption;
import com.itheima.pojo.NumOfStuOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计员工职位人数
     */
    JobOption getEmpJobData();

    /**
     * 统计员工性别人数
     */
    List<Map<String, Object>> getEmpGenderData();

    /**
     * 统计每一个班级的人数
     */
    NumOfStuOption getStuCountData();

    /**
     * 统计学员的学历信息
     */
    List<Map<String, Object>> getStuDegreeData();
}
