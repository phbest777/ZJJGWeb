package org.ph.ssm.ZJJGWeb.service;
import org.ph.ssm.ZJJGWeb.bean.XzhouseMenu;
import org.ph.ssm.ZJJGWeb.bean.XzhouseUsers;
import org.ph.ssm.ZJJGWeb.dao.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;



    public List<XzhouseMenu> getMenuList(String UserCode){

        return loginMapper.SelectMenuList(UserCode);

    }

    public List<XzhouseUsers> getUserList(String UserCode)
    {
        return loginMapper.SelectUsers(UserCode);
    }
}
