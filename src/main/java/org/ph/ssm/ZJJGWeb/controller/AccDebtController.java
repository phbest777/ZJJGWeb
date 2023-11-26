package org.ph.ssm.ZJJGWeb.controller;
import cn.hutool.core.lang.tree.Tree;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.weaver.ast.Or;
import org.ph.ssm.ZJJGWeb.bean.*;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransZjjz;
import org.ph.ssm.ZJJGWeb.bean.XzhouseDeptOrg;
import org.ph.ssm.ZJJGWeb.model.*;
import org.ph.ssm.ZJJGWeb.service.*;
//import org.ph.xzhouse.httpcomentity.DepositRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AccDebtController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private AccDebtService accDebtService;

    @Autowired
    private AccInfoService accInfoService;


    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccDebt/GetJzinfoByUser")
    public QueryAccDebtModel GetGjinfoByUser(@RequestBody JsonNode QueryInfo)
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
        List<XzhouseTransZjjz> JzinfoList = accDebtService.getJzinfoByOrgIDS(OrgIDList);
        //long totalCount=page.getTotal();

        PageInfo pageInfo = new PageInfo(JzinfoList,pageSize);
        long totalCount=pageInfo.getTotal();
        System.out.println("Get JzinfoList by OrgID total is:"+totalCount);
        List<XzhouseTransZjjz> returnList= pageInfo.getList();
        // step4:包装查询后的数据
        //PageInfo pageInfo = new PageInfo(employeeList);
        QueryAccDebtModel queryAccDebtModel=new QueryAccDebtModel();
        if (JzinfoList.size() <= 0) {
            queryAccDebtModel.setCode("999");
            queryAccDebtModel.setMsg("查询失败，没有符合条件的记录");
            queryAccDebtModel.setTotalCount(0);
            return queryAccDebtModel;
        }

        queryAccDebtModel.setCode("200");
        queryAccDebtModel.setMsg("success");
        queryAccDebtModel.setTotalCount(totalCount);
        queryAccDebtModel.setData(returnList);
        queryAccDebtModel.setOrgidlist(xzhouseDeptOrgs);
        return queryAccDebtModel;
    }

    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccDebt/GetJzinfoByMultiChoose")
    public QueryAccDebtModel GetJzinfoByMultiChoose(@RequestBody JsonNode QueryInfo)
    {
        String UserCode=QueryInfo.path("username").asText();
        String Payername=QueryInfo.path("payername").asText();
        String Payeracc=QueryInfo.path("payeracc").asText();
        String Contractno=QueryInfo.path("contractno").asText();
        String Recordno=QueryInfo.path("recordno").asText();
        String Ckr=QueryInfo.path("ckr").asText();
        String Price=QueryInfo.path("price").asText();
        String Flag=QueryInfo.path("flag").asText();
        String StartDataDate=QueryInfo.path("startdatadate").asText();
        String EndDataDate=QueryInfo.path("enddatadate").asText();
        String Isjz=QueryInfo.path("isjz").asText();
        String OrgID=QueryInfo.path("orgid").asText();
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
        ChooseMap.put("recordno",Recordno);
        ChooseMap.put("ckr",Ckr);
        ChooseMap.put("price",Price);
        ChooseMap.put("flag",Flag);
        ChooseMap.put("startdatadate",StartDataDate);
        ChooseMap.put("enddatadate",EndDataDate);
        System.out.print("startdate is:"+StartDataDate+"  enddate is:"+ EndDataDate);
        ChooseMap.put("isjz",Isjz);

        Page page=PageHelper.startPage(pageNum, pageSize);
        List<XzhouseTransZjjz> JzinfoList = null;
        if(OrgID!=null&&!OrgID.isEmpty()) {
            ChooseMap.put("orgid", OrgID);
            JzinfoList=accDebtService.getJzinfoByMultiChoose(ChooseMap);
            System.out.println("Proc is getJzinfoListByMultiChoose");
        }
        else
        {
            ChooseMap.put("OrgIDList",OrgIDList);
            JzinfoList=accDebtService.getJzinfoByMultiChooseWithOrgIDS(ChooseMap);
            System.out.println("Proc is getGjinfoListByMultiChooseWithOrgIDS");
        }
        long totalCount=page.getTotal();

        //System.out.println("orgid is :"+OrgID);
        System.out.println("Get JzinfoList by OrgID total is:"+totalCount);
        PageInfo pageInfo = new PageInfo(JzinfoList,pageSize);
        List<XzhouseTransZjjz> returnList= pageInfo.getList();
        // step4:包装查询后的数据
        //PageInfo pageInfo = new PageInfo(employeeList);
        QueryAccDebtModel queryAccDebtModel=new QueryAccDebtModel();
        if (JzinfoList.size() <= 0) {
            queryAccDebtModel.setCode("999");
            queryAccDebtModel.setMsg("查询失败，没有符合条件的记录");
            queryAccDebtModel.setTotalCount(0);
            return queryAccDebtModel;
        }

        queryAccDebtModel.setCode("200");
        queryAccDebtModel.setMsg("success");
        queryAccDebtModel.setTotalCount(totalCount);
        queryAccDebtModel.setData(returnList);
        return queryAccDebtModel;
    }
/*
    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccDebt/AddJzInfoByDetail")
    public BaseModel AddJzInfoByDetail(@RequestBody JsonNode ParaInfo) throws JsonProcessingException {
        String UserCode=ParaInfo.path("username").asText();
        int Id=ParaInfo.path("id").asInt();
        String JgName=ParaInfo.path("jgname").asText();
        String JgAccount=ParaInfo.path("jgaccount").asText();
        String Agent=ParaInfo.path("agent").asText();
        String CardNo=ParaInfo.path("cardno").asText();
        String Xybh=ParaInfo.path("xybh").asText();
        String RecordNo=ParaInfo.path("recordno").asText();
        String Ckr=ParaInfo.path("ckr").asText();
        String Operator=ParaInfo.path("operator").asText();
        String Flag=ParaInfo.path("flag").asText();
        String Cklb=ParaInfo.path("cklb").asText();
        String Cklsh=ParaInfo.path("cklsh").asText();
        Double Price=ParaInfo.path("price").asDouble();
        //String Code="000";
        //String Msg="记账成功";
        String OrgId=ParaInfo.path("orgid").asText();
        String OrgName=ParaInfo.path("orgname").asText();
        String Zjxz=ParaInfo.path("zjxz").asText();
        String TransDate=ParaInfo.path("transdate").asText();
        String SubmitDate=ParaInfo.path("submitdate").asText();
        String SubmitTime=ParaInfo.path("submittime").asText();
        String AddMode=ParaInfo.path("addmode").asText();

        Map RetMap=httpCommService.MoneyAccount(Xybh,RecordNo,Ckr,Price.toString(),Operator,Cklb,Flag);
        String Code=RetMap.get("Code").toString();
        String Msg=RetMap.get("Msg").toString();
        System.out.println("httptest RetrunCode is:"+Code);
        System.out.println("httptest ReturnMsg is :"+Msg);
        Map updateMap=new HashMap();
        updateMap.put("id",Id);
        updateMap.put("xybh",Xybh);
        updateMap.put("recordno",RecordNo);
        updateMap.put("ckr",Ckr);
        updateMap.put("price",Price);
        updateMap.put("cklsh",Cklsh);
        updateMap.put("transdate",TransDate);
        updateMap.put("isjz","1");
        XzhouseMoneyaccount xzhouseMoneyaccount=new XzhouseMoneyaccount();
        xzhouseMoneyaccount.setXybh(Xybh);
        xzhouseMoneyaccount.setRecordno(RecordNo);
        xzhouseMoneyaccount.setCkr(Ckr);
        xzhouseMoneyaccount.setPrice(Price);
        xzhouseMoneyaccount.setOperator(Operator);
        xzhouseMoneyaccount.setCklb(Cklb);
        xzhouseMoneyaccount.setCklbname(Cklb);
        xzhouseMoneyaccount.setFlag(Flag);
        xzhouseMoneyaccount.setFlaganme(Flag);
        xzhouseMoneyaccount.setCklsh(Cklsh);
        xzhouseMoneyaccount.setCode(Code);
        xzhouseMoneyaccount.setMsg(Msg);
        xzhouseMoneyaccount.setDatadate(SubmitDate);
        xzhouseMoneyaccount.setDatatime(SubmitTime);
        Boolean IsAddSucess=accDebtService.AddMoneyAccount(xzhouseMoneyaccount);
        BaseModel addBasemodel=new BaseModel();
        if(Code.equals("000"))
        {
            if(AddMode.equals("0")) {
                Boolean IsUpdateSucess = accDebtService.UpdateJzInfo(updateMap);
                if (IsUpdateSucess) {
                    addBasemodel.setCode("200");
                    addBasemodel.setMsg("资金记账发送成功,状态更新成功");
                } else {
                    addBasemodel.setCode("999");
                    addBasemodel.setMsg("资金记账发送成功，状态更新失败");
                }
            }
            else
            {
                XzhouseTransZjjz xzhouseTransZjjz=new XzhouseTransZjjz();
                xzhouseTransZjjz.setAgent(Agent);
                xzhouseTransZjjz.setCardno(CardNo);
                xzhouseTransZjjz.setXybh(Xybh);
                xzhouseTransZjjz.setRecordno(RecordNo);
                xzhouseTransZjjz.setCkr(Ckr);
                xzhouseTransZjjz.setPrice(Price);
                xzhouseTransZjjz.setOperator(Operator);
                xzhouseTransZjjz.setZjxz(Zjxz);
                xzhouseTransZjjz.setFlag(Flag);
                xzhouseTransZjjz.setCklsh(Cklsh);
                xzhouseTransZjjz.setTransdate(TransDate);
                xzhouseTransZjjz.setCklb(Cklb);*/
                /*if(Flag.equals("0"))
                {
                    xzhouseTransZjjz.setIsjz("2");
                }
                else
                {
                    xzhouseTransZjjz.setIsjz("0");
                }*/
               /* xzhouseTransZjjz.setIsjz("1");
                Boolean IsAddTransJzInfoSucess=accDebtService.AddTransZjjz(xzhouseTransZjjz);
                if(IsAddTransJzInfoSucess)
                {
                    addBasemodel.setCode("200");
                    addBasemodel.setMsg("资金记账发送成功,数据更新成功");
                }
                else
                {
                    addBasemodel.setCode("999");
                    addBasemodel.setMsg("资金记账发送成功，数据更新失败");
                }
            }
        }
        else
        {
            addBasemodel.setCode("999");
            addBasemodel.setMsg("资金记账发送失败,返回信息："+Msg);
        }
        return addBasemodel;
    }*/

    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccDebt/GetJzinfoNewByUser")
    public QueryAccDebtNewModel GetGjinfoNewByUser(@RequestBody JsonNode QueryInfo)
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
        List<XzhouseTransZjjzNew> JzinfoList = accDebtService.getJzinfoNewByOrgIDS(OrgIDList);
        //long totalCount=page.getTotal();

        PageInfo pageInfo = new PageInfo(JzinfoList,pageSize);
        long totalCount=pageInfo.getTotal();
        System.out.println("Get JzinfoList by OrgID total is:"+totalCount);
        List<XzhouseTransZjjzNew> returnList= pageInfo.getList();
        // step4:包装查询后的数据
        //PageInfo pageInfo = new PageInfo(employeeList);
        QueryAccDebtNewModel queryAccDebtNewModel=new QueryAccDebtNewModel();
        if (JzinfoList.size() <= 0) {
            queryAccDebtNewModel.setCode("999");
            queryAccDebtNewModel.setMsg("查询失败，没有符合条件的记录");
            queryAccDebtNewModel.setTotalCount(0);
            return queryAccDebtNewModel;
        }

        queryAccDebtNewModel.setCode("200");
        queryAccDebtNewModel.setMsg("success");
        queryAccDebtNewModel.setTotalCount(totalCount);
        queryAccDebtNewModel.setData(returnList);
        queryAccDebtNewModel.setOrgidlist(xzhouseDeptOrgs);
        return queryAccDebtNewModel;
    }

    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccDebt/GetJzinfoNewByMultiChoose")
    public QueryAccDebtNewModel GetJzinfoNewByMultiChoose(@RequestBody JsonNode QueryInfo)
    {
        String UserCode=QueryInfo.path("username").asText();
        String Payername=QueryInfo.path("payername").asText();
        String Payeracc=QueryInfo.path("payeracc").asText();
        String Contractno=QueryInfo.path("contractno").asText();
        String Recordno=QueryInfo.path("recordno").asText();
        String Ckr=QueryInfo.path("ckr").asText();
        String Price=QueryInfo.path("price").asText();
        String Flag=QueryInfo.path("flag").asText();
        String StartDataDate=QueryInfo.path("startdatadate").asText();
        String EndDataDate=QueryInfo.path("enddatadate").asText();
        String Isjz=QueryInfo.path("isjz").asText();
        String OrgID=QueryInfo.path("orgid").asText();
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
        ChooseMap.put("recordno",Recordno);
        ChooseMap.put("ckr",Ckr);
        ChooseMap.put("price",Price);
        ChooseMap.put("flag",Flag);
        ChooseMap.put("startdatadate",StartDataDate);
        ChooseMap.put("enddatadate",EndDataDate);
        System.out.print("startdate is:"+StartDataDate+"  enddate is:"+ EndDataDate);
        ChooseMap.put("isjz",Isjz);

        Page page=PageHelper.startPage(pageNum, pageSize);
        List<XzhouseTransZjjzNew> JzinfoList = null;
        if(OrgID!=null&&!OrgID.isEmpty()) {
            ChooseMap.put("orgid", OrgID);
            JzinfoList=accDebtService.getJzinfoNewByMultiChoose(ChooseMap);
            System.out.println("Proc is getJzinfoListByMultiChoose");
        }
        else
        {
            ChooseMap.put("OrgIDList",OrgIDList);
            JzinfoList=accDebtService.getJzinfoNewByMultiChooseWithOrgIDS(ChooseMap);
            System.out.println("Proc is getGjinfoListByMultiChooseWithOrgIDS");
        }
        long totalCount=page.getTotal();

        //System.out.println("orgid is :"+OrgID);
        System.out.println("Get JzinfoList by OrgID total is:"+totalCount);
        PageInfo pageInfo = new PageInfo(JzinfoList,pageSize);
        List<XzhouseTransZjjzNew> returnList= pageInfo.getList();
        // step4:包装查询后的数据
        //PageInfo pageInfo = new PageInfo(employeeList);
        QueryAccDebtNewModel queryAccDebtNewModel=new QueryAccDebtNewModel();
        if (JzinfoList.size() <= 0) {
            queryAccDebtNewModel.setCode("999");
            queryAccDebtNewModel.setMsg("查询失败，没有符合条件的记录");
            queryAccDebtNewModel.setTotalCount(0);
            return queryAccDebtNewModel;
        }

        queryAccDebtNewModel.setCode("200");
        queryAccDebtNewModel.setMsg("success");
        queryAccDebtNewModel.setTotalCount(totalCount);
        queryAccDebtNewModel.setData(returnList);
        return queryAccDebtNewModel;
    }
/*
    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccDebt/AddJzInfoNewByDetail")
    public BaseModel AddJzInfoNewByDetail(@RequestBody JsonNode ParaInfo) throws JsonProcessingException {
        String UserCode=ParaInfo.path("username").asText();
        int Id=ParaInfo.path("id").asInt();
        String JgName=ParaInfo.path("jgname").asText();
        String JgAccount=ParaInfo.path("jgaccount").asText();
        String Agent=ParaInfo.path("agent").asText();
        String CardNo=ParaInfo.path("cardno").asText();
        String Xybh=ParaInfo.path("xybh").asText();
        String RecordNo=ParaInfo.path("recordno").asText();
        String Ckr=ParaInfo.path("ckr").asText();
        String Operator=ParaInfo.path("operator").asText();
        String Flag=ParaInfo.path("flag").asText();
        String Cklb=ParaInfo.path("cklb").asText();
        String Cklsh=ParaInfo.path("cklsh").asText();
        Double Price=ParaInfo.path("price").asDouble();
        //String Code="000";
       // String Msg="记账成功";
        String OrgId=ParaInfo.path("orgid").asText();
        String OrgName=ParaInfo.path("orgname").asText();
        String Zjxz=ParaInfo.path("zjxz").asText();
        String TransDate=ParaInfo.path("transdate").asText();
        String SubmitDate=ParaInfo.path("submitdate").asText();
        String SubmitTime=ParaInfo.path("submittime").asText();
        String AddMode=ParaInfo.path("addmode").asText();

        Map RetMap=httpCommService.MoneyAccount(Xybh,RecordNo,Ckr,Price.toString(),Operator,Cklb,Flag);
         String Code=RetMap.get("Code").toString();
         String Msg=RetMap.get("Msg").toString();
        System.out.println("Price is:"+Price.toString());
        System.out.println("httptest RetrunCode is:"+Code);
        System.out.println("httptest ReturnMsg is :"+Msg);
        Map updateMap=new HashMap();
        updateMap.put("id",Id);
        updateMap.put("xybh",Xybh);
        updateMap.put("recordno",RecordNo);
        updateMap.put("ckr",Ckr);
        updateMap.put("price",Price);
        updateMap.put("cklsh",Cklsh);
        updateMap.put("transdate",TransDate);
        updateMap.put("isjz","1");
        XzhouseMoneyaccount xzhouseMoneyaccount=new XzhouseMoneyaccount();
        xzhouseMoneyaccount.setXybh(Xybh);
        xzhouseMoneyaccount.setRecordno(RecordNo);
        xzhouseMoneyaccount.setCkr(Ckr);
        xzhouseMoneyaccount.setPrice(Price);
        xzhouseMoneyaccount.setOperator(Operator);
        xzhouseMoneyaccount.setCklb(Cklb);
        xzhouseMoneyaccount.setCklbname(Cklb);
        xzhouseMoneyaccount.setFlag(Flag);
        xzhouseMoneyaccount.setFlaganme(Flag);
        xzhouseMoneyaccount.setCklsh(Cklsh);
        xzhouseMoneyaccount.setCode(Code);
        xzhouseMoneyaccount.setMsg(Msg);
        xzhouseMoneyaccount.setDatadate(SubmitDate);
        xzhouseMoneyaccount.setDatatime(SubmitTime);
        Boolean IsAddSucess=accDebtService.AddMoneyAccount(xzhouseMoneyaccount);
        BaseModel addBasemodel=new BaseModel();
        if(Code.equals("000"))
        {
            if(AddMode.equals("0")) {
                Boolean IsUpdateSucess = accDebtService.UpdateJzInfo(updateMap);
                if (IsUpdateSucess) {
                    addBasemodel.setCode("200");
                    addBasemodel.setMsg("资金记账发送成功,状态更新成功");
                } else {
                    addBasemodel.setCode("999");
                    addBasemodel.setMsg("资金记账发送成功，状态更新失败");
                }
            }
            else
            {
                XzhouseTransZjjzNew  xzhouseTransZjjzNew=new XzhouseTransZjjzNew();
                xzhouseTransZjjzNew.setAgent(Agent);
                xzhouseTransZjjzNew.setCardno(CardNo);
                xzhouseTransZjjzNew.setXybh(Xybh);
                xzhouseTransZjjzNew.setRecordno(RecordNo);
                xzhouseTransZjjzNew.setCkr(Ckr);
                xzhouseTransZjjzNew.setPrice(Price);
                xzhouseTransZjjzNew.setOperator(Operator);
                xzhouseTransZjjzNew.setZjxz(Zjxz);
                xzhouseTransZjjzNew.setFlag(Flag);
                xzhouseTransZjjzNew.setCklsh(Cklsh);
                xzhouseTransZjjzNew.setTransdate(TransDate);
                xzhouseTransZjjzNew.setCklb(Cklb);*/
                /*if(Flag.equals("0"))
                {
                    xzhouseTransZjjz.setIsjz("2");
                }
                else
                {
                    xzhouseTransZjjz.setIsjz("0");
                }*/
                /*xzhouseTransZjjzNew.setIsjz("1");
                xzhouseTransZjjzNew.setAreacode("zc");
                Boolean IsAddTransJzInfoNewSucess=accDebtService.AddTransZjjzNew(xzhouseTransZjjzNew);
                if(IsAddTransJzInfoNewSucess)
                {
                    addBasemodel.setCode("200");
                    addBasemodel.setMsg("资金记账发送成功,数据更新成功");
                }
                else
                {
                    addBasemodel.setCode("999");
                    addBasemodel.setMsg("资金记账发送成功，数据更新失败");
                }
            }
        }
        else
        {
            addBasemodel.setCode("999");
            addBasemodel.setMsg("资金记账发送失败,返回信息："+Msg);
        }
        return addBasemodel;
    }*/
}
