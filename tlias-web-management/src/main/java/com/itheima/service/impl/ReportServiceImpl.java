package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.NumOfStuOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        // 1.调用mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData(); // map: pos=教研主管, num=1

        // 2.组装结果，并返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public NumOfStuOption getStuCountData() {
        List<Map<String, Object>> list = studentMapper.countStuNumData();

        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("clazzName")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("value")).toList();

        return new NumOfStuOption(clazzList, dataList);
    }

    @Override
    public List<Map<String, Object>> getStuDegreeData() {
        return studentMapper.countStuDegreeData();
    }
}
