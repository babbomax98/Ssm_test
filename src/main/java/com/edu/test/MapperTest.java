package com.edu.test;

import com.edu.mapper.DepartmentMapper;
import com.edu.pojo.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author shkstart
 * @create 2020-02-10 20:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

/*    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;*/

    /**
     * 测试DepartmentMapper
     */
    @Test
    public void testCRUD(){

        System.out.println(departmentMapper);

        //1、插入几个部门
		departmentMapper.insertSelective(new Department(null, "开发部"));
		departmentMapper.insertSelective(new Department(null, "测试部"));



        System.out.println("批量完成");

    }
}
