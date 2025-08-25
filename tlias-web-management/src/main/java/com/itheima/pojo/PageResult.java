package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 分页结果封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private Long total; // 这个成员变量名要与接口文档保持一致
    private List<T> rows; // 这个成员变量名要与接口文档保持一致
}
