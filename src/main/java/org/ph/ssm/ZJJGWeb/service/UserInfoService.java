package org.ph.ssm.ZJJGWeb.service;
import org.ph.ssm.ZJJGWeb.bean.XzhouseUserRole;
import org.ph.ssm.ZJJGWeb.bean.XzhouseRoles;
import org.ph.ssm.ZJJGWeb.dao.LoginMapper;
import org.ph.ssm.ZJJGWeb.dao.UserInfoMapper;
import org.ph.ssm.ZJJGWeb.bean.XzhouseDeptOrg;
import org.ph.ssm.ZJJGWeb.bean.XzhouseUserRoleDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    public List<XzhouseUserRoleDept> getUserRoleDept(String UserCode){
        return userInfoMapper.SelectUserRoleDept(UserCode);
    }

    public List<XzhouseRoles> getSubUserRoles(String RoleCode){
        return userInfoMapper.SelectSubUserRoles(RoleCode);
    }

    public List<XzhouseUserRole> getUserRoleByUser(String UserCode){
        return userInfoMapper.SelectUserRoleByUser(UserCode);
    }

    public List<XzhouseUserRoleDept> getSubUserRoleDept(String UserCode){
        return userInfoMapper.SelectSubUserRoleDept(UserCode);
    }

    public int getCountDeptOrgs(String DeptCode){
        return userInfoMapper.SelectCountDeptOrgs(DeptCode);
    }

    public Boolean UpdateUserPass(Map UpdateMap){
        return userInfoMapper.UpdateUserPass(UpdateMap);
    }
}
