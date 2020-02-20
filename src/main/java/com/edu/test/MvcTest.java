package com.edu.test;

import com.edu.pojo.Employee;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-02-12 20:16
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations ={"classpath:applicationContext.xml","classpath:springmvc.xml"})
public class MvcTest {
    //传入springmvc的ioc
    @Autowired
    WebApplicationContext context;
    //虚拟mvc请求，获取到处理结果

    MockMvc mockMvc;

    //before用于每次使用前进行初始化
    @Before
    public void initMockMvc(){
     mockMvc =  MockMvcBuilders.webAppContextSetup(context).build();

    }
    @Test
    public void testpage() throws Exception {
        //模拟请求拿到返回值
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1"))
                .andReturn();
        //请求成功之后，请求域中会有pageInfo，我们可以取出pageInfo进行验证
        MockHttpServletRequest request=result.getRequest();
        PageInfo pa= (PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前页码："+pa.getPageNum());
        System.out.println("总页码："+pa.getPages());
        System.out.println("总记录数："+pa.getTotal());
        System.out.println("在页面需要连续显示的页码");
        int[] arr=pa.getNavigatepageNums();
        for(int i:arr){
            System.out.print(" "+i);
        }
        List<Employee> list=pa.getList();
        for (Employee employee:list){
            System.out.println("id:"+employee.getdId()+"==="+"name:"+employee.getName()+"==="+"gender:"+employee.getGender());
        }


    }
}
