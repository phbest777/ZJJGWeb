package org.ph.ssm.ZJJGWeb.controller;
import com.fasterxml.jackson.databind.JsonNode;
import org.aspectj.weaver.ast.Or;
import org.ph.ssm.ZJJGWeb.bean.*;
import org.ph.ssm.ZJJGWeb.bean.XzhouseParaAccinfo;
import org.ph.ssm.ZJJGWeb.model.*;
import org.ph.ssm.ZJJGWeb.service.AccInfoService;
import org.ph.ssm.ZJJGWeb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class AccInfoController {
    @Autowired
    private AccInfoService accInfoService;
    @Autowired
    private LoginService loginService;

    @CrossOrigin
    @PostMapping(value = "/Zjjg/AccInfo/GetAccInfoList")
    public QueryAccInfoModel GetAccinfoByOrgID(@RequestBody JsonNode QueryInfo)
    {
        String UserCode=QueryInfo.path("username").asText();
        XzhouseUsers userInfo=loginService.getUserList(UserCode).get(0);
        String OrgID=userInfo.getUserDeptcode();
        List<XzhouseParaAccinfo> AccinfoList = accInfoService.getAccInfoByOrgID(OrgID);
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
}
