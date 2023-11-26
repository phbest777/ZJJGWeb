package org.ph.ssm.ZJJGWeb.dao;
import org.ph.ssm.ZJJGWeb.bean.XzhouseDeptOrg;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.ph.ssm.ZJJGWeb.bean.XzhouseUserRoleDept;
import org.ph.ssm.ZJJGWeb.bean.XzhouseRoles;
import org.ph.ssm.ZJJGWeb.bean.XzhouseUserRole;
import org.ph.ssm.ZJJGWeb.bean.XzhouseUsers;
import java.util.Map;
public interface UserInfoMapper {
    List<XzhouseUserRoleDept> SelectUserRoleDept(String UserCode);
    List<XzhouseUserRoleDept> SelectSubUserRoleDept(String UserCode);
    List<XzhouseRoles> SelectSubUserRoles(String RoleCode);

    List<XzhouseUserRole> SelectUserRoleByUser(String UserCode);

    int SelectCountDeptOrgs(String DeptCode);
    Boolean UpdateUserPass(Map UpdateMap);
}
