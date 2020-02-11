package com.edu.mapper;

import com.edu.pojo.Department;
import com.edu.pojo.DepartmentExample;
import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer deptId);

    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByExample(DepartmentExample example);

    Department selectByPrimaryKey(Integer deptId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}