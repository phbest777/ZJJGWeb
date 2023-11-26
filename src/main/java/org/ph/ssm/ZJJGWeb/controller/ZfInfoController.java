package org.ph.ssm.ZJJGWeb.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.ph.ssm.ZJJGWeb.bean.*;
import org.ph.ssm.ZJJGWeb.bean.XzhouseParaZfacc;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransZfinfo;
import org.ph.ssm.ZJJGWeb.model.*;
import org.ph.ssm.ZJJGWeb.service.ZfInfoService;
import org.ph.ssm.ZJJGWeb.service.LoginService;
import org.ph.ssm.ZJJGWeb.bean.XzhouseDeptOrg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ph.ssm.ZJJGWeb.httpcomm.XzhouseCommImp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ZfInfoController {
    @Autowired
    private ZfInfoService zfInfoService;

    @Autowired
    private LoginService loginService;
/*
    @Autowired
    private HttpCommService httpCommService;*/

    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccPay/GetRcvAccinfoList")
    public QueryRcvAccModel GetRcvAccinfoList(@RequestBody JsonNode QueryInfo)
    {
        String UserCode=QueryInfo.path("username").asText();
        XzhouseUsers userInfo=loginService.getUserList(UserCode).get(0);
        List<XzhouseParaZfacc> zfaccList = zfInfoService.getZfAccInfoByAll();
        System.out.println("Get zfaccList by OrgID total is:"+zfaccList.size());

        // step4:包装查询后的数据
        //PageInfo pageInfo = new PageInfo(employeeList);
        QueryRcvAccModel queryRcvAccModel=new QueryRcvAccModel();
        if (zfaccList.size() <= 0) {
            queryRcvAccModel.setCode("999");
            queryRcvAccModel.setMsg("查询失败，没有符合条件的记录");
            return queryRcvAccModel;
        }

        queryRcvAccModel.setCode("200");
        queryRcvAccModel.setMsg("success");
        queryRcvAccModel.setData(zfaccList);
        return queryRcvAccModel;
    }

    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccPay/GetZfTransListByAll")
    public QueryZfTransModel GetGjinfoByUser(@RequestBody JsonNode QueryInfo)
    {
        String UserCode=QueryInfo.path("username").asText();
        int pageNum=QueryInfo.path("pageno").asInt();
        int pageSize=QueryInfo.path("pagesize").asInt();
        String queryMode=QueryInfo.path("querymode").asText();
        XzhouseUsers userInfo=loginService.getUserList(UserCode).get(0);
        String DeptCode=userInfo.getUserDeptcode();
        List<XzhouseDeptOrg> xzhouseDeptOrgs=loginService.getOrgIDSByDeptCode(DeptCode);//获取部门对应的机构号，部门可以为普通机构也可以为管辖部门或者支行
        List<String> OrgIDList=xzhouseDeptOrgs.stream().map(orgidlist->orgidlist.getOrgid()).distinct().collect(Collectors.toList());
        Map FlagMap=new HashMap();
        if(queryMode.equals("0"))
        {
            FlagMap.put("zfflag","");//模式0，全选不看iszf状态
        }
        else
        {
            FlagMap.put("zfflag","9");//模式1,只选择iszf=9已入账记录
        }
        Page page=PageHelper.startPage(pageNum, pageSize);
        List<XzhouseTransZfinfo> xzhouseTransZfinfoList = zfInfoService.getZfTransByAll(FlagMap);
        //long totalCount=page.getTotal();





        PageInfo pageInfo = new PageInfo(xzhouseTransZfinfoList,pageSize);
        long totalCount=pageInfo.getTotal();
        System.out.println("Get xzhouseTransZfinfoList  total is:"+totalCount);
        List<XzhouseTransZfinfo> returnList= pageInfo.getList();
        // step4:包装查询后的数据
        //PageInfo pageInfo = new PageInfo(employeeList);
        QueryZfTransModel queryZfTransModel=new QueryZfTransModel();
        if (xzhouseTransZfinfoList.size() <= 0) {
            queryZfTransModel.setCode("999");
            queryZfTransModel.setMsg("查询失败，没有符合条件的记录");
            queryZfTransModel.setTotalCount(0);
            return queryZfTransModel;
        }

        queryZfTransModel.setCode("200");
        queryZfTransModel.setMsg("success");
        queryZfTransModel.setTotalCount(totalCount);
        queryZfTransModel.setData(returnList);
        return queryZfTransModel;
    }

    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccPay/GetZfTransListByMultiChoose")
    public QueryZfTransModel GetZfTransListByMultiChoose(@RequestBody JsonNode QueryInfo)
    {
        String UserCode=QueryInfo.path("username").asText();
        String Rcvname=QueryInfo.path("rcvname").asText();
        String Rcvacc=QueryInfo.path("rcvacc").asText();
        String Amt=QueryInfo.path("amt").asText();
        String Iszf=QueryInfo.path("zfflag").asText();
        String StartDataDate=QueryInfo.path("startdate").asText();
        String EndDataDate=QueryInfo.path("enddate").asText();
        String QueryMode=QueryInfo.path("querymode").asText();
        int pageNum=QueryInfo.path("pageno").asInt();
        int pageSize=QueryInfo.path("pagesize").asInt();
        Map ChooseMap=new HashMap();
        ChooseMap.put("rcvname",Rcvname);
        ChooseMap.put("rcvacc",Rcvacc);
        ChooseMap.put("amt",Amt);
        if(QueryMode.equals("0")) {
            ChooseMap.put("iszf", Iszf);
        }
        else
        {
            ChooseMap.put("iszf","9");
        }
        ChooseMap.put("startdatadate",StartDataDate);
        ChooseMap.put("enddatadate",EndDataDate);
        System.out.print("startdate is:"+StartDataDate+"  enddate is:"+ EndDataDate);

        Page page=PageHelper.startPage(pageNum, pageSize);
        List<XzhouseTransZfinfo> TransZfinfoList = null;
        TransZfinfoList=zfInfoService.getZfTransByMultiChoose(ChooseMap);
        long totalCount=page.getTotal();

        //System.out.println("orgid is :"+OrgID);
        System.out.println("Get TransZfinfoList  total is:"+totalCount);
        PageInfo pageInfo = new PageInfo(TransZfinfoList,pageSize);
        List<XzhouseTransZfinfo> returnList= pageInfo.getList();
        // step4:包装查询后的数据
        //PageInfo pageInfo = new PageInfo(employeeList);
        QueryZfTransModel queryZfTransModel=new QueryZfTransModel();
        if (TransZfinfoList.size() <= 0) {
            queryZfTransModel.setCode("999");
            queryZfTransModel.setMsg("查询失败，没有符合条件的记录");
            queryZfTransModel.setTotalCount(0);
            return queryZfTransModel;
        }

        queryZfTransModel.setCode("200");
        queryZfTransModel.setMsg("success");
        queryZfTransModel.setTotalCount(totalCount);
        queryZfTransModel.setData(returnList);
        return queryZfTransModel;
    }
    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccPay/UpZfInfoByDetail")
    public BaseModel UpZfInfoByDetail(@RequestBody JsonNode QueryInfo) throws IOException {
        String UserCode=QueryInfo.path("username").asText();
        String Rcvname=QueryInfo.path("rcvname").asText();
        String Rcvacc=QueryInfo.path("rcvacc").asText();
        String Bankname=QueryInfo.path("bankname").asText();
        String Contractno=QueryInfo.path("contractno").asText();
        Double Amt=QueryInfo.path("amt").asDouble();
        String Zflsh=QueryInfo.path("addwords").asText();
        String Hxlsh=QueryInfo.path("hxlsh").asText();
        String DataDate=QueryInfo.path("datadate").asText();
        String DataTime=QueryInfo.path("datatime").asText();
        Map updateMap=new HashMap();
        updateMap.put("rcvname",Rcvname);
        updateMap.put("rcvacc",Rcvacc);
        updateMap.put("contractno",Contractno);
        updateMap.put("amt",Amt);
        updateMap.put("zflsh",Zflsh);
        updateMap.put("datadate",DataDate);
        updateMap.put("iszf","5");
        //发送授权通知
        XzhouseCommImp xzhouseCommImp=new XzhouseCommImp();
        List<String> Paras=xzhouseCommImp.GetCommIni(System.getProperty("user.dir")+"\\HttpComm.properties");
        Map sendUpMap=new HashMap();
        sendUpMap.put("xybh",Contractno);
        sendUpMap.put("zfqqlsh",Zflsh);
        sendUpMap.put("skfzh",Rcvacc);
        sendUpMap.put("zfje",Amt);
        BaseModel upzfinfoBasemodel=new BaseModel();//定义返回值
        try {
            String PostStr = xzhouseCommImp.MapToJsonStr(sendUpMap);
            String resdata = xzhouseCommImp.GetHttPost(Paras.get(2).toString() + PostStr);
            JsonNode retNode = xzhouseCommImp.DealHttpPost(resdata);//获取返回值

            String Code = retNode.path("code").asText();
            String Msg = retNode.path("msg").asText();
            if (Code.equals("000") || Msg.equals("更新失败:状态已更新不用重复发送"))//支付状态更新成功
            {
                Boolean isSucess = zfInfoService.UpdateZfInfo(updateMap);
                if (isSucess) {
                    upzfinfoBasemodel.setCode("200");
                    upzfinfoBasemodel.setMsg("发送支付授权成功,状态更新成功");
                } else {
                    upzfinfoBasemodel.setCode("999");
                    upzfinfoBasemodel.setMsg("发送支付授权成功,状态更新失败,尝试重发");
                }
            } else {
                upzfinfoBasemodel.setCode("999");
                upzfinfoBasemodel.setMsg("发送支付授权失败,对方返回信息" + Msg);
            }
        }
        catch (Exception ex)
        {
            upzfinfoBasemodel.setCode("999");
            upzfinfoBasemodel.setMsg("发送支付授权失败,原因:"+ex.toString());
        }
        return upzfinfoBasemodel;
    }

/*
    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccPay/CheckZfInfoRedo")
    public BaseModel CheckZfInfoRedo(@RequestBody JsonNode QueryInfo) throws IOException {
        String UserCode=QueryInfo.path("username").asText();
        String Zflsh=QueryInfo.path("addwords").asText();
        Map RetMap=httpCommService.ConfirmPay(Zflsh);
        String Code=RetMap.get("Code").toString();
        String Msg=RetMap.get("Msg").toString();
        System.out.println("httptest RetrunCode is:"+Code);
        System.out.println("httptest ReturnMsg is :"+Msg);
        BaseModel CheckRedoBasemodel=new BaseModel();
        if(Code.equals("000"))
        {
            CheckRedoBasemodel.setCode("200");
            CheckRedoBasemodel.setMsg("资金支付确认发送成功");
        }
        else
        {
            CheckRedoBasemodel.setCode("999");
            CheckRedoBasemodel.setMsg("资金支付确认发送失败:"+Msg);
        }
        return CheckRedoBasemodel;
    }*/
}
