package com.itheima.service;

import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;

public interface LogService {
    /**
     * 日志信息的分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageResult<OperateLog> page(Integer page, Integer pageSize);
}
