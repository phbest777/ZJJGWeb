package org.ph.ssm.ZJJGWeb.bean;


public class XzhouseDeptOrg {

  private String id;
  private String deptcode;
  private String orgid;
  private String uptdate;
  private String orgname;
  private String deptname;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getDeptcode() {
    return deptcode;
  }

  public void setDeptcode(String deptcode) {
    this.deptcode = deptcode;
  }


  public String getOrgid() {
    return orgid;
  }

  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }


  public String getUptdate() {
    return uptdate;
  }

  public void setUptdate(String uptdate) {
    this.uptdate = uptdate;
  }

  public String getOrgname(){return orgname;}

  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }
  public String getDeptname(){return deptname;}

  public void setDeptname(String deptname) {
    this.deptname = deptname;
  }
}
