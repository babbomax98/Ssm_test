package com.edu.service;

import com.edu.mapper.EmployeeMapper;
import com.edu.pojo.Employee;
import com.edu.pojo.EmployeeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-02-12 19:53
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    public Employee getemp(Integer id){
       Employee employee= employeeMapper.selectByPrimaryKey(id);
       return employee;
    }

    public List<Employee> getAll() {


        return employeeMapper.selectByExampleWithDept(null);
    }

//    保存
    public void saveEmp(Employee employee){
        employeeMapper.insertSelective(employee);
    }

//添加
    public void updateEmp(Employee employee){
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

//    删除
    public void deleteEmp(Integer id){
        employeeMapper.deleteByPrimaryKey(id);

    }

//    全部删除

    public void deleteAll(List<Integer> ids){
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        //delete from xxx where emp_id in(1,2,3)
        criteria.andIdIn(ids);
        employeeMapper.deleteByExample(example);

    }
//校验
    public boolean checkUser(String empName){
        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria= example.createCriteria();
        criteria.andNameEqualTo(empName);
        long count=employeeMapper.countByExample(example);
        return count == 0;


    }
}
