package org.ph.ssm.ZJJGWeb.bean;

public class XzhouseUserRoleDept {
    private Integer id;
    private String userCode;
    private String userName;
    private String userPass;
    private String userDeptcode;
    private String userEmail;
    private String roleCode;
    private String roleName;
    private String orgid;
    private String orgname;
    private String deptName;

    private String supRolecode;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserDeptcode() {
        return userDeptcode;
    }

    public void setUserDeptcode(String userDeptcode) {
        this.userDeptcode = userDeptcode;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getSupRolecode() {
        return supRolecode;
    }

    public void setSupRolecode(String supRolecode) {
        this.supRolecode = supRolecode;
    }
}
