package org.ph.ssm.test;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.ph.ssm.ZJJGWeb.bean.LoginReturnData;
import org.ph.ssm.ZJJGWeb.bean.XzhouseMenu;
import org.ph.ssm.ZJJGWeb.dao.LoginMapper;
import org.ph.ssm.ZJJGWeb.service.LoginService;
import org.junit.runner.RunWith;
//import org.ph.xzhouse.httpcomentity.AccountList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.ph.xzhouse.httpcom.Services;

import java.util.List;
import java.util.Map;

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
    /*
    @Test
    public void TestGetHttp() throws JsonProcessingException {
        String IniFileName="C:\\GitRepository\\xzhousehttpcom\\ParaIni.ini";
        Services httpServ=new Services(IniFileName);
        String JsonStr=httpServ.QueryAccountList(null,null,null,null);
        Map map=httpServ.JsonToEntity_QueryAccountList(JsonStr);
        System.out.println("ReturnCode is:"+map.get("Code"));
        System.out.println("ReturnMsg is:"+map.get("Msg"));
        List<AccountList> accList=(List<AccountList>)map.get("Data");
        System.out.println("ReturnData is:"+accList.get(0).getCorpName());

    }
    @Test
    public void TestGetHttp2() throws JsonProcessingException {
        HttpCommService httpCommService=new HttpCommService();
        Map retMap=httpCommService.QueryAccountList(null,null,"509274683914",null);
        String retCode=retMap.get("Code").toString();
        String retMsg=retMap.get("Msg").toString();
        Double xx=222.1212;
        List<AccountList> retList=(List<AccountList>)retMap.get("Data");
        String retData=retList.get(0).getCorpName();
        System.out.println("Return Code is:"+retCode);
        System.out.println("Return Msg is:"+retMsg);
        System.out.println("Return Data is:"+retData);
        System.out.println(xx.toString());
    }*/
    @Test
    public void Test2()
    {
        Double p=0.00;
        System.out.println("value is:"+p.toString());
    }
}
