package org.ph.ssm.ZJJGWeb.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.ph.ssm.ZJJGWeb.bean.*;
import org.ph.ssm.ZJJGWeb.bean.XzhouseUserRoleDept;
import org.ph.ssm.ZJJGWeb.model.*;
import org.ph.ssm.ZJJGWeb.service.LoginService;
import org.ph.ssm.ZJJGWeb.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class UserInfoController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserInfoService userInfoService;

    @CrossOrigin
    @PostMapping(value = "/Zjjg/PersonnelManagement/UserManagement/GetUserinfoList")
    public QueryUserRoleDeptModel GetUserinfoList(@RequestBody JsonNode QueryInfo)
    {
        String UserCode=QueryInfo.path("username").asText();
        int pageNum=QueryInfo.path("pageno").asInt();
        int pageSize=QueryInfo.path("pagesize").asInt();
        XzhouseUsers userInfo=loginService.getUserList(UserCode).get(0);
        String DeptCode=userInfo.getUserDeptcode();
       // int DeptOrgsCount=userInfoService.getCountDeptOrgs(DeptCode);
        String UserRoleCode=userInfoService.getUserRoleByUser(UserCode).get(0).getRoleCode();
        List<XzhouseRoles> xzhouseRolesList=userInfoService.getSubUserRoles(UserRoleCode);
        List<XzhouseDeptOrg> xzhouseDeptOrgs=loginService.getOrgIDSByDeptCode(DeptCode);//获取部门对应的机构号，部门可以为普通机构也可以为管辖部门或者支行
        List<String> OrgIDList=xzhouseDeptOrgs.stream().map(orgidlist->orgidlist.getOrgid()).distinct().collect(Collectors.toList());

        List<XzhouseUserRoleDept> xzhouseUserRoleDeptList=new ArrayList<XzhouseUserRoleDept>();
        if(xzhouseDeptOrgs.size()>1) {
            xzhouseUserRoleDeptList = userInfoService.getUserRoleDept(UserCode);
        }
        else
        {
            xzhouseUserRoleDeptList=userInfoService.getSubUserRoleDept(UserCode);
        }
        //long totalCount=page.getTotal();





        PageInfo pageInfo = new PageInfo(xzhouseUserRoleDeptList,pageSize);
        long totalCount=pageInfo.getTotal();
        System.out.println("Get xzhouseUserRoleDeptList by OrgID total is:"+totalCount);
        List<XzhouseUserRoleDept> returnList= pageInfo.getList();
        // step4:包装查询后的数据
        //PageInfo pageInfo = new PageInfo(employeeList);
        QueryUserRoleDeptModel queryUserRoleDeptModel=new QueryUserRoleDeptModel();
        if (xzhouseUserRoleDeptList.size() <= 0) {
            queryUserRoleDeptModel.setCode("999");
            queryUserRoleDeptModel.setMsg("查询失败，没有符合条件的记录");
            queryUserRoleDeptModel.setTotalCount(0);
            return queryUserRoleDeptModel;
        }

        queryUserRoleDeptModel.setCode("200");
        queryUserRoleDeptModel.setMsg("success");
        queryUserRoleDeptModel.setTotalCount(totalCount);
        queryUserRoleDeptModel.setData(returnList);
        queryUserRoleDeptModel.setOrgidlist(xzhouseDeptOrgs);
        queryUserRoleDeptModel.setSubrolelist(xzhouseRolesList);
        return queryUserRoleDeptModel;
    }

    @CrossOrigin
    @PostMapping(value = "/Zjjg/PersonnelManagement/UserManagement/GetUserinfo")
    public QueryUserInfoModel GetUserinfo(@RequestBody JsonNode QueryInfo)
    {
        String UserCode=QueryInfo.path("username").asText();
        List<XzhouseUsers> xzhouseUsersList=loginService.getUserList(UserCode);
        XzhouseUsers userInfo=xzhouseUsersList.get(0);
        // step4:包装查询后的数据
        //PageInfo pageInfo = new PageInfo(employeeList);
        QueryUserInfoModel queryUserInfoModel=new QueryUserInfoModel();
        if (xzhouseUsersList.size() <= 0) {
            queryUserInfoModel.setCode("999");
            queryUserInfoModel.setMsg("查询失败，没有符合条件的记录");
            return queryUserInfoModel;
        }

        queryUserInfoModel.setCode("200");
        queryUserInfoModel.setMsg("success");
        queryUserInfoModel.setData(userInfo);
        return queryUserInfoModel;
    }

    @CrossOrigin
    @PostMapping(value = "/Zjjg/PersonnelManagement/UserManagement/UpdateUserPass")
    public BaseModel UpdateUserPass(@RequestBody JsonNode QueryInfo)
    {
        String UserCode=QueryInfo.path("userCode").asText();
        String UserName=QueryInfo.path("userName").asText();
        String OriPass=QueryInfo.path("oriPass").asText();
        String NewPass=QueryInfo.path("newPass").asText();

        Map updatePassMap=new HashMap();
        updatePassMap.put("UserCode",UserCode);
        updatePassMap.put("UserName",UserName);
        updatePassMap.put("OriPass",OriPass);
        updatePassMap.put("NewPass",NewPass);
        updatePassMap.put("UptDate",LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        Boolean Flag=userInfoService.UpdateUserPass(updatePassMap);
        BaseModel UpdatePassModel=new BaseModel();
        if (!Flag) {
            UpdatePassModel.setCode("999");
            UpdatePassModel.setMsg("更新失败");
            return UpdatePassModel;
        }
        UpdatePassModel.setCode("200");
        UpdatePassModel.setMsg("success");
        return UpdatePassModel;
    }
}
