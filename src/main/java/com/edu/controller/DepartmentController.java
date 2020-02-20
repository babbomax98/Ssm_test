package com.edu.controller;

import com.edu.bean.Msg;
import com.edu.pojo.Department;
import com.edu.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-02-17 18:44
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ResponseBody
    @RequestMapping("/depts")
    public Msg getDepts(){

        List<Department> list=departmentService.getDepts();

        return Msg.success().add("depts",list);
    }


}
