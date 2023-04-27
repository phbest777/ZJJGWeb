package org.ph.ssm.ZJJGWeb.controller;
import cn.hutool.core.lang.tree.Tree;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.ph.ssm.ZJJGWeb.bean.*;
import org.ph.ssm.ZJJGWeb.bean.LoginReturnData;
import org.ph.ssm.ZJJGWeb.bean.XzhouseMenu;
import org.ph.ssm.ZJJGWeb.model.*;
import org.ph.ssm.ZJJGWeb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @CrossOrigin
    @PostMapping(value = "/login")
    public LoginModel getLoginReturnData(@RequestBody JsonNode userInfo) {

        System.out.println("login start");
        String userCode=userInfo.path("username").asText();
        String userPass=userInfo.path("password").asText();
        List<XzhouseUsers> userList=loginService.getUserList(userCode);
        LoginModel loginModel=new LoginModel();
        System.out.println("userCode is:"+userCode+";"+"userPass is:"+userPass);
        //System.out.println("List size is:"+userList.size()+"; getpass is:"+userList.get(0).getUserPass());
        if(userList.size()>0&&(userList.get(0).getUserPass().equals(userPass))) {
            loginModel.setCode("200");
            loginModel.setMsg("success");
        }
        else
        {
            loginModel.setCode("999");
            loginModel.setMsg("用户名或密码不正确");
        }
        ReturnDataLogin returnDataLogin=new ReturnDataLogin();
        returnDataLogin.setAccessToken("admin-accessToken");
        loginModel.setData(returnDataLogin);
        //System.out.print(loginModel.getData());


        return loginModel;
    }

    @CrossOrigin
    @PostMapping(value = "/userInfo")
    public LoginUserInfoModel getLoginUserReturnData(@RequestBody JsonNode userInfo)
    {
        String usercode=userInfo.path("username").asText();
        LoginUserInfoModel loginUserInfoModel=new LoginUserInfoModel();
        loginUserInfoModel.setCode("200");
        loginUserInfoModel.setMsg("success");
        List<String> permissionsList=new ArrayList<String>();
        permissionsList.add(usercode);
        ReturnDataUserInfo returnDataUserInfo=new ReturnDataUserInfo();
        returnDataUserInfo.setPermissions(permissionsList);
        returnDataUserInfo.setUsername(usercode);
        returnDataUserInfo.setAvatar("https://i.gtimg.cn/club/item/face/img/2/15922_100.gif");
        loginUserInfoModel.setData(returnDataUserInfo);
        return loginUserInfoModel;
    }

    @CrossOrigin
    @PostMapping(value = "/menu/navigate")
    public LoginMenuModel getLoginMenuReturnData(@RequestBody JsonNode userInfo)
    {
          String userCode=userInfo.path("username").asText();
          //System.out.println("userName is :"+userName);
          LoginMenuModel loginMenuModel=new LoginMenuModel();
          List<XzhouseMenu> MenuList=loginService.getMenuList(userCode);
          if(MenuList.size()>0) {
              loginMenuModel.setCode("200");
              loginMenuModel.setMsg("success");

              LoginReturnData loginReturnData = new LoginReturnData(MenuList);
              List<Tree<String>> MenuTreeList = loginReturnData.getData();
              loginMenuModel.setData(MenuTreeList);
          }
          else
          {
              loginMenuModel.setCode("999");
              loginMenuModel.setMsg("获取列表失败");
          }
        return loginMenuModel;
    }

    @CrossOrigin
    @PostMapping(value = "/logout")
    public BaseModel getLogout()
    {
        BaseModel logoutModel=new BaseModel();
        logoutModel.setCode("200");
        logoutModel.setMsg("success");
        return logoutModel;
    }
}
