package com.edu.controller;

import com.edu.bean.Msg;
import com.edu.pojo.Employee;
import com.edu.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2020-02-12 19:50
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;




    /*
    * 编辑更改
    * */

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg updtEmp(Employee employee){

        employeeService.updateEmp(employee);
        return Msg.success();
    }
    /*
     * 删除
     * */

    @ResponseBody
    @RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
    public Msg deleteEmp(@PathVariable String ids){
        if(ids.contains("-")){
            String[] Str_ids=ids.split("-");
            List<Integer> del_ids=new ArrayList<>();
            for (String iid:Str_ids){
                del_ids.add(Integer.parseInt(iid));
            }
            employeeService.deleteAll(del_ids);
        }else{
            Integer id=Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }

        return Msg.success();
    }

    /*
     * 查找
     * */
    @ResponseBody
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    public Msg getemp(@PathVariable("id") Integer id){
        Employee employee=employeeService.getemp(id);
        return Msg.success().add("emp",employee);
    }

    /*
     * 后台校验
     * */

    @ResponseBody
    @RequestMapping("/checkuser")
    public Msg checkuser(String empName){
        boolean b=employeeService.checkUser(empName);
        if (b){
            return Msg.success();
        }else{
            return Msg.fail();
        }

    }


    /*
     * 添加用户
     * */
    @ResponseBody
    @RequestMapping(value = "/emp",method=RequestMethod.POST)
    public Msg saveEmployee(@Valid Employee employee, BindingResult result){
        if (result.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fielderror : errors) {
                System.out.println("错误字段名" + fielderror.getField());
                System.out.println("错误字段信息"+fielderror.getDefaultMessage());
                map.put(fielderror.getField(),fielderror.getDefaultMessage());
            }
            return Msg.fail().add("errorsFields",map);
        }else {
            employeeService.saveEmp(employee);

            return Msg.success();
        }
    }

    /*
     * 分页
     * */
    @RequestMapping(value="emps")
    @ResponseBody
    public Msg  getEmpWithJson(@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        PageHelper.startPage(pn,5);
        List<Employee> emps=employeeService.getAll();
        PageInfo page=new PageInfo(emps,5);

        return Msg.success().add("pageInfo",page);
    }


   // @RequestMapping("/emps")
    public String getemps(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        System.out.println("hello world....");
        PageHelper.startPage(pn,5);
        List<Employee> emps=employeeService.getAll();
        PageInfo page=new PageInfo(emps,5);

        model.addAttribute("pageInfo",page);

        return "List";
    }

}
