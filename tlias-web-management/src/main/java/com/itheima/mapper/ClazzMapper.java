package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 新增班级
     */
    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) values(#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void insert(Clazz clazz);

    /**
     * 查询所有班级
     */
    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz order by update_time desc ")
    List<Clazz> findAll();

    /**
     * 条件查询班级信息
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 根据id删除班级
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /**
     * 根据id查询班级
     */
    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz where id = #{id}")
    Clazz getById(Integer id);

    /**
     * 修改班级
     */
    @Update("update clazz set name = #{name}, room = #{room}, begin_date = #{beginDate}, end_date = #{endDate}, master_id = #{masterId}, subject = #{subject}, update_time = #{updateTime} where id = #{id}")
    void update(Clazz clazz);

    /**
     * 根据id获取班级里的学生数
     */
    @Select("select count(*) from student where clazz_id = #{id}")
    Integer getStudentCount(Integer id);
}
