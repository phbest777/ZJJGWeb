package org.ph.ssm.ZJJGWeb.controller;
import cn.hutool.core.lang.tree.Tree;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.ph.ssm.ZJJGWeb.bean.*;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransGjinfo;
import org.ph.ssm.ZJJGWeb.model.*;
import org.ph.ssm.ZJJGWeb.service.AccCollectService;
import org.ph.ssm.ZJJGWeb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String OrgID=userInfo.getUserDeptcode();
        Page page=PageHelper.startPage(pageNum, pageSize);
        List<XzhouseTransGjinfo> GjinfoList = accCollectService.getGjinfoListByOrgID(OrgID);
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
