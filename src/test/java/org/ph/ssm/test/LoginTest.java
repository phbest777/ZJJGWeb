package org.ph.ssm.test;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.ph.ssm.ZJJGWeb.bean.LoginReturnData;
import org.ph.ssm.ZJJGWeb.bean.XzhouseMenu;
import org.ph.ssm.ZJJGWeb.dao.LoginMapper;
import org.ph.ssm.ZJJGWeb.service.LoginService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LoginTest {

    @Autowired
    LoginMapper loginMapper;

    @Autowired
    LoginService loginService;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void GetLoginReturnData()
    {

        String UserCode="00001";
        List<XzhouseMenu> MenuList=loginService.getMenuList(UserCode);
        LoginReturnData loginReturnData=new LoginReturnData(MenuList);
        System.out.print("get return data");

    }
}
