package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     *查询所有部门数据
     * */
    // 方式一：手动结果映射
    /*@Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
    })*/

    // 方式二：起别名
    // @Select("select id, name, create_time as createTime, update_time as updateTime from dept order by update_time desc;")

    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();

    /**
     * 根据id删除部门
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 新增部门
     */
    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    /**
     * 根据ID查询部门数据
     */
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    /**
     * 更新部门
     */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);

    /**
     * 根据id获取部门里的员工数
     */
    @Select("select count(*) from emp where dept_id = #{id}")
    Integer getEmpCount(Integer id);
}
