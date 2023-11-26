package org.ph.ssm.ZJJGWeb.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.ph.ssm.ZJJGWeb.bean.*;
import org.ph.ssm.ZJJGWeb.bean.XzhouseParaAccinfo;
import org.ph.ssm.ZJJGWeb.model.*;
import org.ph.ssm.ZJJGWeb.service.AccInfoService;
import org.ph.ssm.ZJJGWeb.service.LoginService;
import org.ph.ssm.ZJJGWeb.bean.XzhouseDeptOrg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class AccInfoController {
    @Autowired
    private AccInfoService accInfoService;
    @Autowired
    private LoginService loginService;

    /*@Autowired
    private HttpCommService httpCommService;*/

    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccInfo/GetAccInfoList")
    public QueryAccInfoModel GetAccinfoByOrgID(@RequestBody JsonNode QueryInfo)
    {
        String UserCode=QueryInfo.path("username").asText();
        XzhouseUsers userInfo=loginService.getUserList(UserCode).get(0);
        String DeptCode=userInfo.getUserDeptcode();
        List<XzhouseDeptOrg> xzhouseDeptOrgs=loginService.getOrgIDSByDeptCode(DeptCode);
        List<String> OrgIDList=xzhouseDeptOrgs.stream().map(orgidlist->orgidlist.getOrgid()).distinct().collect(Collectors.toList());
        List<XzhouseParaAccinfo> AccinfoList = accInfoService.getAccInfoByOrgIDList(OrgIDList);
        System.out.println("Get AccinfoList by OrgID total is:"+AccinfoList.size());

        // step4:包装查询后的数据
        //PageInfo pageInfo = new PageInfo(employeeList);
        QueryAccInfoModel queryAccInfoModel=new QueryAccInfoModel();
        if (AccinfoList.size() <= 0) {
            queryAccInfoModel.setCode("999");
            queryAccInfoModel.setMsg("查询失败，没有符合条件的记录");
            return queryAccInfoModel;
        }

        queryAccInfoModel.setCode("200");
        queryAccInfoModel.setMsg("success");
        queryAccInfoModel.setData(AccinfoList);
        return queryAccInfoModel;
    }
/*
    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccManagement/GetJgAccinfoByUser")
    public QueryJgAccInfoModel GetJgAccinfoByUser(@RequestBody JsonNode QueryInfo) throws JsonProcessingException {
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
        List<XzhouseJgAccinfo> xzhouseJgAccinfoList=accInfoService.GetJgAccount(AccinfoList);
        int totalCount=xzhouseJgAccinfoList.size();
        page.setTotal(totalCount);
        int startIndex=(pageNum-1)*pageSize;
        int endIndex=Math.min(startIndex+pageSize,totalCount);
        page.addAll(xzhouseJgAccinfoList.subList(startIndex,endIndex));
        //PageInfo srcpageInfo = new PageInfo(AccinfoList,pageSize);
        PageInfo pageInfo=new PageInfo<>(page);
        System.out.println("Get xzhouseJgAccinfoList by OrgID total is:"+totalCount);
        List<XzhouseJgAccinfo> returnList= pageInfo.getList();
        // step4:包装查询后的数据
        //PageInfo pageInfo = new PageInfo(employeeList);
        QueryJgAccInfoModel queryJgAccInfoModel=new QueryJgAccInfoModel();
        if (xzhouseJgAccinfoList.size() <= 0) {
            queryJgAccInfoModel.setCode("999");
            queryJgAccInfoModel.setMsg("查询失败，没有符合条件的记录");
            queryJgAccInfoModel.setTotalCount(0);
            return queryJgAccInfoModel;
        }

        queryJgAccInfoModel.setCode("200");
        queryJgAccInfoModel.setMsg("success");
        queryJgAccInfoModel.setTotalCount(totalCount);
        queryJgAccInfoModel.setData(returnList);
        queryJgAccInfoModel.setOrgidlist(xzhouseDeptOrgs);
        return queryJgAccInfoModel;

    }

    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccManagement/GetJgAccinfoByMultiChoose")
    public QueryJgAccInfoModel GetJgAccinfoByMultiChoose(@RequestBody JsonNode QueryInfo) throws JsonProcessingException {
        String UserCode=QueryInfo.path("username").asText();
        int pageNum=QueryInfo.path("pageno").asInt();
        int pageSize=QueryInfo.path("pagesize").asInt();
        String payerName=QueryInfo.path("payername").asText();
        String payerAcc=QueryInfo.path("payeracc").asText();
        String contractNo=QueryInfo.path("contractno").asText();
        String orgId=QueryInfo.path("orgid").asText();
        String useFlag=QueryInfo.path("useflag").asText();
        XzhouseUsers userInfo=loginService.getUserList(UserCode).get(0);
        String DeptCode=userInfo.getUserDeptcode();
        List<XzhouseDeptOrg> xzhouseDeptOrgs=loginService.getOrgIDSByDeptCode(DeptCode);//获取部门对应的机构号，部门可以为普通机构也可以为管辖部门或者支行
        List<String> OrgIDList=xzhouseDeptOrgs.stream().map(orgidlist->orgidlist.getOrgid()).distinct().collect(Collectors.toList());
        List<XzhouseParaAccinfo> AccinfoList=new ArrayList<XzhouseParaAccinfo>();
        List<XzhouseJgAccinfo> xzhouseJgAccinfoList=new ArrayList<XzhouseJgAccinfo>();
        if(payerAcc.isEmpty()&&payerName.isEmpty()&&contractNo.isEmpty())//账号，账户名称，协议编号都为空
        {
            if(orgId.isEmpty())//机构号为空，只根据平帐状态进行查询
            {
                AccinfoList=accInfoService.getAccInfoByOrgIDList(OrgIDList);
                if(useFlag.equals("3"))
                {
                    xzhouseJgAccinfoList=accInfoService.GetJgAccount(AccinfoList);//对账标识为2，所有对账结果都选
                }
                else
                {
                    //对账标识不为2，选取对应的对账结果
                    xzhouseJgAccinfoList=accInfoService.GetJgAccount(AccinfoList).stream().filter(jgacclist->jgacclist.getUseFlag().equals(useFlag)).collect(Collectors.toList());
                }
            }
            else //机构号不为空，根据机构号和平帐状态查询
            {
                AccinfoList=accInfoService.getAccInfoByOrgID(orgId);
                if(useFlag.equals("3")||useFlag.isEmpty())
                {
                    xzhouseJgAccinfoList=accInfoService.GetJgAccount(AccinfoList);
                }
                else
                {
                    xzhouseJgAccinfoList=accInfoService.GetJgAccount(AccinfoList).stream().filter(jgacclist->jgacclist.getUseFlag().equals(useFlag)).collect(Collectors.toList());
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
            if(useFlag.equals("3")||useFlag.isEmpty())
            {
                xzhouseJgAccinfoList=accInfoService.GetJgAccount(AccinfoList);
            }
            else
            {
                xzhouseJgAccinfoList=accInfoService.GetJgAccount(AccinfoList).stream().filter(jgacclist->jgacclist.getUseFlag().equals(useFlag)).collect(Collectors.toList());
            }
        }
        //Page page=PageHelper.startPage(pageNum, pageSize);
        Page page=new Page(pageNum,pageSize);
        int totalCount=xzhouseJgAccinfoList.size();
        page.setTotal(totalCount);
        int startIndex=(pageNum-1)*pageSize;
        int endIndex=Math.min(startIndex+pageSize,totalCount);
        page.addAll(xzhouseJgAccinfoList.subList(startIndex,endIndex));
        //PageInfo srcpageInfo = new PageInfo(AccinfoList,pageSize);
        PageInfo pageInfo=new PageInfo<>(page);
        System.out.println("Get xzhouseJgAccinfoList by OrgID total is:"+totalCount);
        List<XzhouseJgAccinfo> returnList= pageInfo.getList();
        // step4:包装查询后的数据
        //PageInfo pageInfo = new PageInfo(employeeList);
        QueryJgAccInfoModel queryJgAccInfoModel=new QueryJgAccInfoModel();
        if (xzhouseJgAccinfoList.size() <= 0) {
            queryJgAccInfoModel.setCode("999");
            queryJgAccInfoModel.setMsg("查询失败，没有符合条件的记录");
            queryJgAccInfoModel.setTotalCount(0);
            return queryJgAccInfoModel;
        }

        queryJgAccInfoModel.setCode("200");
        queryJgAccInfoModel.setMsg("success");
        queryJgAccInfoModel.setTotalCount(totalCount);
        queryJgAccInfoModel.setData(returnList);
        queryJgAccInfoModel.setOrgidlist(xzhouseDeptOrgs);
        return queryJgAccInfoModel;

    }
    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccManagement/AddJgAccinfoByDetail")
    public BaseModel AddJgAccinfoByDetail(@RequestBody JsonNode QueryInfo) throws JsonProcessingException
    {
        String UserCode=QueryInfo.path("username").asText();
        String orgId=QueryInfo.path("orgid").asText();
        String orgName=QueryInfo.path("orgname").asText();
        String zhMc=QueryInfo.path("zhmc").asText();
        String corpName=QueryInfo.path("corpname").asText();
        String jgAccount=QueryInfo.path("jgaccount").asText();
        Map RetMap=httpCommService.OpenAccount(zhMc,corpName,jgAccount);
        String Code=RetMap.get("Code").toString();
        String Msg=RetMap.get("Msg").toString();
        System.out.println("openAccount RetrunCode is:"+Code);
        System.out.println("openAccount ReturnMsg is :"+Msg);
        BaseModel addBasemodel=new BaseModel();
        if(Code.equals("000"))
        {
            addBasemodel.setCode("200");
            addBasemodel.setMsg(Msg);
        }
        else
        {
            addBasemodel.setCode("999");
            addBasemodel.setMsg(Msg);
        }
        return addBasemodel;
    }
*/
}
