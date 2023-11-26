package org.ph.ssm.ZJJGWeb.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.ph.ssm.ZJJGWeb.bean.*;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransGjinfo;
import org.ph.ssm.ZJJGWeb.bean.XzhouseDeptOrg;
import org.ph.ssm.ZJJGWeb.model.*;
import org.ph.ssm.ZJJGWeb.service.AccCollectService;
import org.ph.ssm.ZJJGWeb.service.LoginService;
import org.ph.ssm.ZJJGWeb.service.AccInfoService;
//import org.ph.xzhouse.httpcomentity.DepositRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AccCollectController {



    @Autowired
    private LoginService loginService;
    @Autowired
    private AccCollectService accCollectService;

    @Autowired
    private AccInfoService accInfoService;


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
        //long totalCount=page.getTotal();





        PageInfo pageInfo = new PageInfo(GjinfoList,pageSize);
        long totalCount=pageInfo.getTotal();
        System.out.println("Get GjinfoList by OrgID total is:"+totalCount);
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
        ChooseMap.put("amt",Amt);
        ChooseMap.put("startdatadate",StartDataDate);
        ChooseMap.put("enddatadate",EndDataDate);
        System.out.print("startdate is:"+StartDataDate+"  enddate is:"+ EndDataDate);
        ChooseMap.put("isgj",Isgj);

        Page page=PageHelper.startPage(pageNum, pageSize);
        List<XzhouseTransGjinfo> GjinfoList = null;
        if(OrgID!=null&&!OrgID.isEmpty()) {
            ChooseMap.put("orgid", OrgID);
            GjinfoList=accCollectService.getGjinfoListByMultiChoose(ChooseMap);
            System.out.println("Proc is getGjinfoListByMultiChoose");
        }
        else
        {
            ChooseMap.put("OrgIDList",OrgIDList);
            GjinfoList=accCollectService.getGjinfoListByMultiChooseWithOrgIDS(ChooseMap);
            System.out.println("Proc is getGjinfoListByMultiChooseWithOrgIDS");
        }
        long totalCount=page.getTotal();

        //System.out.println("orgid is :"+OrgID);
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
    /*
    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccCollect/AddGjInfoByDetail")
    public BaseModel AddGjInfoByDetail(@RequestBody JsonNode ParaInfo) throws JsonProcessingException {
        String UserCode=ParaInfo.path("username").asText();
        String JgAccount=ParaInfo.path("payeracc").asText();
        Double Ckje=ParaInfo.path("amt").asDouble();
        String Yhlsh=ParaInfo.path("hxlsh").asText();
        String Gapslsh=ParaInfo.path("gapslsh").asText();
        //String Code="000";
        //String Msg="归集成功";
        String OrgId=ParaInfo.path("orgid").asText();
        String OrgName=ParaInfo.path("orgname").asText();
        String AccountName=ParaInfo.path("payername").asText();
        String SubmitDate=ParaInfo.path("submitdate").asText();
        String SubmitTime=ParaInfo.path("submittime").asText();
        String ConTractno=ParaInfo.path("contractno").asText();
        String GjAcc=ParaInfo.path("gjacc").asText();
        String GjName=ParaInfo.path("gjname").asText();
        String AddWords=ParaInfo.path("addwords").asText();
        String AddMode=ParaInfo.path("addmode").asText();
        String DataDate=ParaInfo.path("datadate").asText();
        String Agent=ParaInfo.path("agent").asText();
        Map RetMap=httpCommService.AddGJRecord(JgAccount,Ckje.toString(),Yhlsh);
        String Code=RetMap.get("Code").toString();
        String Msg=RetMap.get("Msg").toString();
        System.out.println("httptest RetrunCode is:"+Code);
        System.out.println("httptest ReturnMsg is :"+Msg);
        Map updateMap=new HashMap();
        updateMap.put("payeracc",JgAccount);
        updateMap.put("contractno",ConTractno);
        updateMap.put("amt",Ckje);
        updateMap.put("hxlsh",Yhlsh);
        updateMap.put("datadate",DataDate);
        updateMap.put("isgj","1");
        XzhouseAddgjrecord xzhouseAddgjrecord=new XzhouseAddgjrecord();
        xzhouseAddgjrecord.setJgaccount(JgAccount);
        xzhouseAddgjrecord.setCkje(Ckje);
        xzhouseAddgjrecord.setYhlsh(Yhlsh);
        xzhouseAddgjrecord.setCode(Code);
        xzhouseAddgjrecord.setMsg(Msg);
        xzhouseAddgjrecord.setOrgid(OrgId);
        xzhouseAddgjrecord.setOrgname(OrgName);
        xzhouseAddgjrecord.setAccountname(AccountName);
        xzhouseAddgjrecord.setDatadate(SubmitDate);
        xzhouseAddgjrecord.setDatatime(SubmitTime);
        Boolean IsAddSucess=accCollectService.AddGjRecord(xzhouseAddgjrecord);
        BaseModel addBasemodel=new BaseModel();
        if(Code.equals("000"))
        {
            if(AddMode.equals("0")) {
                Boolean IsUpdateSucess = accCollectService.UpdateGjInfo(updateMap);
                if (IsUpdateSucess) {
                    addBasemodel.setCode("200");
                    addBasemodel.setMsg("资金归集发送成功,状态更新成功");
                } else {
                    addBasemodel.setCode("999");
                    addBasemodel.setMsg("资金归集发送成功，状态更新失败");
                }
            }
            else
            {
                XzhouseTransGjinfo xzhouseTransGjinfo=new XzhouseTransGjinfo();
                xzhouseTransGjinfo.setPayeracc(JgAccount);
                xzhouseTransGjinfo.setPayername(AccountName);
                xzhouseTransGjinfo.setGjacc(GjAcc);
                xzhouseTransGjinfo.setGjname(GjName);
                xzhouseTransGjinfo.setContractno(ConTractno);
                xzhouseTransGjinfo.setAmt(Ckje);
                xzhouseTransGjinfo.setGapslsh(Gapslsh);
                xzhouseTransGjinfo.setHxlsh(Yhlsh);
                xzhouseTransGjinfo.setOrgid(OrgId);
                xzhouseTransGjinfo.setOrgname(OrgName);
                xzhouseTransGjinfo.setIsgj("1");
                xzhouseTransGjinfo.setAddwords(AddWords);
                xzhouseTransGjinfo.setDatadate(SubmitDate);
                xzhouseTransGjinfo.setDatatime(SubmitTime);
                xzhouseTransGjinfo.setAgent(Agent);
                Boolean IsAddTransGjInfoSucess=accCollectService.AddTransGjInfo(xzhouseTransGjinfo);
                if(IsAddTransGjInfoSucess)
                {
                    addBasemodel.setCode("200");
                    addBasemodel.setMsg("资金归集发送成功,数据更新成功");
                }
                else
                {
                    addBasemodel.setCode("999");
                    addBasemodel.setMsg("资金归集发送成功，数据更新失败");
                }
            }
        }
        else
        {
            addBasemodel.setCode("999");
            addBasemodel.setMsg("资金归集发送失败,返回信息："+Msg);
        }
         return addBasemodel;
    }
    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccCollect/GetGjJzValidByUser")
    public QueryGjJzValidModel GetGjJzValidByUser(@RequestBody JsonNode QueryInfo) throws JsonProcessingException {
        String UserCode=QueryInfo.path("username").asText();
        int pageNum=QueryInfo.path("pageno").asInt();
        int pageSize=QueryInfo.path("pagesize").asInt();

        XzhouseUsers userInfo=loginService.getUserList(UserCode).get(0);
        String DeptCode=userInfo.getUserDeptcode();
        List<XzhouseDeptOrg> xzhouseDeptOrgs=loginService.getOrgIDSByDeptCode(DeptCode);//获取部门对应的机构号，部门可以为普通机构也可以为管辖部门或者支行
        List<String> OrgIDList=xzhouseDeptOrgs.stream().map(orgidlist->orgidlist.getOrgid()).distinct().collect(Collectors.toList());

        List<XzhouseParaAccinfo> AccinfoList = accInfoService.getAccInfoByOrgIDList(OrgIDList);
        //Page page=PageHelper.startPage(pageNum, pageSize);
        Page page=new Page(pageNum,pageSize);
        List<XzhouseGjJzValid> xzhouseGjJzValidList=accCollectService.GetGjJzValid(AccinfoList);
        int totalCount=xzhouseGjJzValidList.size();
        page.setTotal(totalCount);
        int startIndex=(pageNum-1)*pageSize;
        int endIndex=Math.min(startIndex+pageSize,totalCount);
        page.addAll(xzhouseGjJzValidList.subList(startIndex,endIndex));
        //PageInfo srcpageInfo = new PageInfo(AccinfoList,pageSize);
        PageInfo pageInfo=new PageInfo<>(page);
        System.out.println("Get GjJzValidList by OrgID total is:"+totalCount);
        List<XzhouseGjJzValid> returnList= pageInfo.getList();
        // step4:包装查询后的数据
        //PageInfo pageInfo = new PageInfo(employeeList);
        QueryGjJzValidModel queryGjJzValidModel=new QueryGjJzValidModel();
        if (xzhouseGjJzValidList.size() <= 0) {
            queryGjJzValidModel.setCode("999");
            queryGjJzValidModel.setMsg("查询失败，没有符合条件的记录");
            queryGjJzValidModel.setTotalCount(0);
            return queryGjJzValidModel;
        }

        queryGjJzValidModel.setCode("200");
        queryGjJzValidModel.setMsg("success");
        queryGjJzValidModel.setTotalCount(totalCount);
        queryGjJzValidModel.setData(returnList);
        queryGjJzValidModel.setOrgidlist(xzhouseDeptOrgs);
        return queryGjJzValidModel;

    }

    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccCollect/GetGjJzValidByMultiChoose")
    public QueryGjJzValidModel GetGjJzValidByMultiChoose(@RequestBody JsonNode QueryInfo) throws JsonProcessingException {
        String UserCode=QueryInfo.path("username").asText();
        int pageNum=QueryInfo.path("pageno").asInt();
        int pageSize=QueryInfo.path("pagesize").asInt();
        String payerName=QueryInfo.path("payername").asText();
        String payerAcc=QueryInfo.path("payeracc").asText();
        String contractNo=QueryInfo.path("contractno").asText();
        String orgId=QueryInfo.path("orgid").asText();
        String isValid=QueryInfo.path("isvalid").asText();
        XzhouseUsers userInfo=loginService.getUserList(UserCode).get(0);
        String DeptCode=userInfo.getUserDeptcode();
        List<XzhouseDeptOrg> xzhouseDeptOrgs=loginService.getOrgIDSByDeptCode(DeptCode);//获取部门对应的机构号，部门可以为普通机构也可以为管辖部门或者支行
        List<String> OrgIDList=xzhouseDeptOrgs.stream().map(orgidlist->orgidlist.getOrgid()).distinct().collect(Collectors.toList());
        List<XzhouseParaAccinfo> AccinfoList=new ArrayList<XzhouseParaAccinfo>();
        List<XzhouseGjJzValid> xzhouseGjJzValidList=new ArrayList<XzhouseGjJzValid>();
        if(payerAcc.isEmpty()&&payerName.isEmpty()&&contractNo.isEmpty())//账号，账户名称，协议编号都为空
        {
            if(orgId.isEmpty())//机构号为空，只根据平帐状态进行查询
            {
                AccinfoList=accInfoService.getAccInfoByOrgIDList(OrgIDList);
                if(isValid.equals("2"))
                {
                    xzhouseGjJzValidList=accCollectService.GetGjJzValid(AccinfoList);//对账标识为2，所有对账结果都选
                }
                else
                {
                    //对账标识不为2，选取对应的对账结果
                    xzhouseGjJzValidList=accCollectService.GetGjJzValid(AccinfoList).stream().filter(gjjzlist->gjjzlist.getIsvalid().equals(isValid)).collect(Collectors.toList());
                }
            }
            else //机构号不为空，根据机构号和平帐状态查询
            {
                AccinfoList=accInfoService.getAccInfoByOrgID(orgId);
                if(isValid.equals("2")||isValid.isEmpty())
                {
                    xzhouseGjJzValidList=accCollectService.GetGjJzValid(AccinfoList);
                }
                else
                {
                    xzhouseGjJzValidList=accCollectService.GetGjJzValid(AccinfoList).stream().filter(gjjzlist->gjjzlist.getIsvalid().equals(isValid)).collect(Collectors.toList());
                }
            }
        }
        else//账号，账户名称，协议编号不全为空
        {
            Map corpMap=new HashMap();
            corpMap.put("payername",payerName);
            corpMap.put("payeracc",payerAcc);
            corpMap.put("contractno",contractNo);
            AccinfoList=accInfoService.getAccInfoByCorpInfo(corpMap);
            if(isValid.equals("2")||isValid.isEmpty())
            {
                xzhouseGjJzValidList=accCollectService.GetGjJzValid(AccinfoList);
            }
            else
            {
                xzhouseGjJzValidList=accCollectService.GetGjJzValid(AccinfoList).stream().filter(gjjzlist->gjjzlist.getIsvalid().equals(isValid)).collect(Collectors.toList());
            }
        }
        //Page page=PageHelper.startPage(pageNum, pageSize);
        Page page=new Page(pageNum,pageSize);
        int totalCount=xzhouseGjJzValidList.size();
        page.setTotal(totalCount);
        int startIndex=(pageNum-1)*pageSize;
        int endIndex=Math.min(startIndex+pageSize,totalCount);
        page.addAll(xzhouseGjJzValidList.subList(startIndex,endIndex));
        //PageInfo srcpageInfo = new PageInfo(AccinfoList,pageSize);
        PageInfo pageInfo=new PageInfo<>(page);
        System.out.println("Get GjJzValidList by OrgID total is:"+totalCount);
        List<XzhouseGjJzValid> returnList= pageInfo.getList();
        // step4:包装查询后的数据
        //PageInfo pageInfo = new PageInfo(employeeList);
        QueryGjJzValidModel queryGjJzValidModel=new QueryGjJzValidModel();
        if (xzhouseGjJzValidList.size() <= 0) {
            queryGjJzValidModel.setCode("999");
            queryGjJzValidModel.setMsg("查询失败，没有符合条件的记录");
            queryGjJzValidModel.setTotalCount(0);
            return queryGjJzValidModel;
        }

        queryGjJzValidModel.setCode("200");
        queryGjJzValidModel.setMsg("success");
        queryGjJzValidModel.setTotalCount(totalCount);
        queryGjJzValidModel.setData(returnList);
        queryGjJzValidModel.setOrgidlist(xzhouseDeptOrgs);
        return queryGjJzValidModel;

    }*/
}
