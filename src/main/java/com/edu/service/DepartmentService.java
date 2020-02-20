package com.edu.service;

import com.edu.mapper.DepartmentMapper;
import com.edu.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-02-17 18:44
 */
@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @ResponseBody
    @RequestMapping("/depts")
    public List<Department> getDepts(){

        List<Department> list=departmentMapper.selectByExample(null);


        return list;
    }


}
