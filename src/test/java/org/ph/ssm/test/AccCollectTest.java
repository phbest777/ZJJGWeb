package org.ph.ssm.test;
//import com.jacob.com.Dispatch;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.ph.ssm.ZJJGWeb.bean.LoginReturnData;
import org.ph.ssm.ZJJGWeb.bean.XzhouseDeptOrg;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransGjinfo;
import org.ph.ssm.ZJJGWeb.dao.AccCollectMapper;
import org.ph.ssm.ZJJGWeb.service.AccCollectService;
import org.ph.ssm.ZJJGWeb.dao.LoginMapper;
import org.ph.ssm.ZJJGWeb.service.LoginService;
import org.ph.ssm.ZJJGWeb.bean.XzhouseUsers;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.ph.xzhouse.httpcom.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AccCollectTest {
    @Autowired
     AccCollectMapper accCollectMapper;
    @Autowired
    LoginMapper loginMapper;

    @Autowired
    AccCollectService accCollectService;
    @Autowired
    LoginService loginService;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void GetAccList()
    {

        List<String> OrgIDS=new ArrayList<>();
        OrgIDS.add("06963");
        OrgIDS.add("06957");
        List<XzhouseTransGjinfo> GjInfoList=accCollectService.getGjinfoListByOrgIDS(OrgIDS);
        System.out.print("get return data is :"+GjInfoList.size());

    }
    @Test
    public void GetAccListByOrgIDS()
    {
        Map ChooseMap=new HashMap();
        ChooseMap.put("payername","徐州茂星置业有限公司");
        ChooseMap.put("payeracc","");
        ChooseMap.put("contractno","");
        ChooseMap.put("amt","");
        ChooseMap.put("startdatadate","");
        ChooseMap.put("enddatadate","");
        ChooseMap.put("isgj","");
        List<String> OrgIDS=new ArrayList<>();
        OrgIDS.add("06963");
        OrgIDS.add("06957");
        ChooseMap.put("OrgIDList",OrgIDS);
        List<XzhouseTransGjinfo> GjInfoList=accCollectService.getGjinfoListByMultiChooseWithOrgIDS(ChooseMap);
        //List<XzhouseTransGjinfo> GjInfoList=accCollectService.getGjinfoListByMultiChoose(ChooseMap);
        System.out.print("get return data is :"+GjInfoList.size());

    }
    @Test
    public void GetGjInfoListByOrgIDS()
    {
        String UserCode="00001";
        XzhouseUsers xzhouseUsers=loginService.getUserList(UserCode).get(0);
        String DeptCode=xzhouseUsers.getUserDeptcode();
        List<XzhouseDeptOrg> xzhouseDeptOrgs=loginService.getOrgIDSByDeptCode(DeptCode);
        List<String> OrgIDList=xzhouseDeptOrgs.stream().map(orgidlist->orgidlist.getOrgid()).distinct().collect(Collectors.toList());
        System.out.println("OrgIDlist count is:"+OrgIDList.size());

    }
}
