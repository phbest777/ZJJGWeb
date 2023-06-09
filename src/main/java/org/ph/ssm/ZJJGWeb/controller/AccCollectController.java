package org.ph.ssm.ZJJGWeb.controller;
import cn.hutool.core.lang.tree.Tree;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.weaver.ast.Or;
import org.ph.ssm.ZJJGWeb.bean.*;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransGjinfo;
import org.ph.ssm.ZJJGWeb.bean.XzhouseDeptOrg;
import org.ph.ssm.ZJJGWeb.model.*;
import org.ph.ssm.ZJJGWeb.service.AccCollectService;
import org.ph.ssm.ZJJGWeb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AccCollectController {



    @Autowired
    private LoginService loginService;
    @Autowired
    private AccCollectService accCollectService;

    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccCollect/GetGjinfoByUser")
    public QueryAccCollectModel GetGjinfoByUser(@RequestBody JsonNode QueryInfo)
    {
        String UserCode=QueryInfo.path("username").asText();
        int pageNum=QueryInfo.path("pageno").asInt();
        int pageSize=QueryInfo.path("pagesize").asInt();
        XzhouseUsers userInfo=loginService.getUserList(UserCode).get(0);
        String DeptCode=userInfo.getUserDeptcode();
        List<XzhouseDeptOrg> xzhouseDeptOrgs=loginService.getOrgIDSByDeptCode(DeptCode);//获取部门对应的机构号，部门可以为普通机构也可以为管辖部门或者支行
        List<String> OrgIDList=xzhouseDeptOrgs.stream().map(orgidlist->orgidlist.getOrgid()).distinct().collect(Collectors.toList());
        Map<String,String> OrgIDMaps=xzhouseDeptOrgs.stream().collect(Collectors.toMap(XzhouseDeptOrg::getId,XzhouseDeptOrg::getOrgid,(V1,V2)->V1));
        Page page=PageHelper.startPage(pageNum, pageSize);
        List<XzhouseTransGjinfo> GjinfoList = accCollectService.getGjinfoListByOrgIDS(OrgIDList);
        long totalCount=page.getTotal();


        System.out.println("Get GjinfoList by OrgID total is:"+totalCount);


        PageInfo pageInfo = new PageInfo(GjinfoList,pageSize);

        List<XzhouseTransGjinfo> returnList= pageInfo.getList();
        // step4:包装查询后的数据
        //PageInfo pageInfo = new PageInfo(employeeList);
        QueryAccCollectModel queryAccCollectModel=new QueryAccCollectModel();
        if (GjinfoList.size() <= 0) {
            queryAccCollectModel.setCode("999");
            queryAccCollectModel.setMsg("查询失败，没有符合条件的记录");
            queryAccCollectModel.setTotalCount(0);
            return queryAccCollectModel;
        }

        queryAccCollectModel.setCode("200");
        queryAccCollectModel.setMsg("success");
        queryAccCollectModel.setTotalCount(totalCount);
        queryAccCollectModel.setData(returnList);
        queryAccCollectModel.setOrgidlist(xzhouseDeptOrgs);
        return queryAccCollectModel;
    }

    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccCollect/GetGjinfoByMultiChoose")
    public QueryAccCollectModel GetGjinfoByMultiChoose(@RequestBody JsonNode QueryInfo)
    {
        String UserCode=QueryInfo.path("username").asText();
        String Payername=QueryInfo.path("payername").asText();
        String Payeracc=QueryInfo.path("payeracc").asText();
        String Contractno=QueryInfo.path("contractno").asText();
        String Amt=QueryInfo.path("amt").asText();
        String StartDataDate=QueryInfo.path("startdatadate").asText();
        String EndDataDate=QueryInfo.path("enddatadate").asText();
        String Isgj=QueryInfo.path("isgj").asText();
        int pageNum=QueryInfo.path("pageno").asInt();
        int pageSize=QueryInfo.path("pagesize").asInt();
        XzhouseUsers userInfo=loginService.getUserList(UserCode).get(0);
        String DeptCode=userInfo.getUserDeptcode();
        List<XzhouseDeptOrg> xzhouseDeptOrgs=loginService.getOrgIDSByDeptCode(DeptCode);
        List<String> OrgIDList=xzhouseDeptOrgs.stream().map(orgidlist->orgidlist.getOrgid()).distinct().collect(Collectors.toList());
        Map ChooseMap=new HashMap();
        ChooseMap.put("payername",Payername);
        ChooseMap.put("payeracc",Payeracc);
        ChooseMap.put("contractno",Contractno);
        ChooseMap.put("amt",Amt);
        ChooseMap.put("startdatadate",StartDataDate);
        ChooseMap.put("enddatadate",EndDataDate);
        System.out.print("startdate is:"+StartDataDate+"  enddate is:"+ EndDataDate);
        ChooseMap.put("isgj",Isgj);
        ChooseMap.put("OrgIDList",OrgIDList);
        Page page=PageHelper.startPage(pageNum, pageSize);
        List<XzhouseTransGjinfo> GjinfoList = accCollectService.getGjinfoListByMultiChooseWithOrgIDS(ChooseMap);
        long totalCount=page.getTotal();


        System.out.println("Get GjinfoList by OrgID total is:"+totalCount);


        PageInfo pageInfo = new PageInfo(GjinfoList,pageSize);

        List<XzhouseTransGjinfo> returnList= pageInfo.getList();
        // step4:包装查询后的数据
        //PageInfo pageInfo = new PageInfo(employeeList);
        QueryAccCollectModel queryAccCollectModel=new QueryAccCollectModel();
        if (GjinfoList.size() <= 0) {
            queryAccCollectModel.setCode("999");
            queryAccCollectModel.setMsg("查询失败，没有符合条件的记录");
            queryAccCollectModel.setTotalCount(0);
            return queryAccCollectModel;
        }

        queryAccCollectModel.setCode("200");
        queryAccCollectModel.setMsg("success");
        queryAccCollectModel.setTotalCount(totalCount);
        queryAccCollectModel.setData(returnList);
        return queryAccCollectModel;
    }

}
