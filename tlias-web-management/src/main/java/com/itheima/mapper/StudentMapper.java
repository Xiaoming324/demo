package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    /**
     * 学员列表数据的条件分页查询
     */
    List<Student> list(StudentQueryParam studentQueryParam);

    /**
     * 添加学员信息
     */
    @Insert("insert into student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, create_time, update_time) values (#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void insert(Student student);

    /**
     * 根据主键ID查询学员的信息
     */
    @Select("select id, name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, violation_count, violation_score, create_time, update_time from student where id = #{id}")
    Student getById(Integer id);

    /**
     * 修改学员的数据信息
     */
    void updateById(Student student);

    /**
     * 批量删除学员信息
     */
    void deleByIds(List<Integer> ids);

    /**
     * 修改学员的违纪处理数据信息
     */
    @Update("update student set violation_score = violation_score + #{score}, violation_count = violation_count + 1, update_time = #{updateTime} where id = #{id}")
    void punishById(Integer id, Integer score, LocalDateTime updateTime);

    /**
     * 统计每一个班级的人数
     */
    @MapKey("clazzName")
    List<Map<String, Object>> countStuNumData();

    /**
     * 统计学员的学历信息
     */
    @MapKey("name")
    List<Map<String, Object>> countStuDegreeData();
}
