package com.edu.mapper;

import com.edu.pojo.Employee;
import com.edu.pojo.EmployeeExample;
import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}