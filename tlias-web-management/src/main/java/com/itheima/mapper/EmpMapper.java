package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    // ----------------------------原始分页查询实现方式----------------------------
    /**
     * 查询总记录数
     */
    /*@Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    Long count();*/

    /**
     * 分页查询
     */
    /*@Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc limit #{start},#{pageSize}")
    List<Emp> list(Integer start, Integer pageSize);*/

    // ----------------------------PageHelper分页查询实现方式----------------------------
    // @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
    //List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 条件查询员工信息
     *
     * @param empQueryParam
     */
    List<Emp> list(EmpQueryParam empQueryParam);


    /**
     * 新增员工基本信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id") // 获取到生成的主键 -- 主键返回 // 调用完insert方法，就会将id拿到并且赋值给emp的id属性
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    /**
     * 查询全部员工信息
     */
    @Select("select id, username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time from emp")
    List<Emp> findAll();

    /**
     * 根据ID批量删除员工的基本信息
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询员工信息以及员工工作经历信息
     */
    Emp getById(Integer id);

    /**
     * 根据ID更新员工的基本信息
     */
    void updateById(Emp emp);

    /**
     * 统计员工职位人数
     */
    @MapKey("pos")
    // 这个注解在这里实际不会生效，因为返回的是List而不是Map）主要是不加会有误报 mybatis插件导致
    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    /**
     * 根据用户名和密码查询员工信息
     */
    @Select("select id, username, name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);

    /**
     * 根据id修改员工密码
     */
    @Update("update emp set password=#{password} where id = #{id}")
    void updatePassword(Integer id, String password);
}
