package org.ph.ssm.ZJJGWeb.model;
import org.ph.ssm.ZJJGWeb.bean.XzhouseUserRoleDept;
import java.util.List;
import org.ph.ssm.ZJJGWeb.bean.XzhouseDeptOrg;
import org.ph.ssm.ZJJGWeb.bean.XzhouseRoles;

public class QueryUserRoleDeptModel  extends QueryBaseModel {
    private List<XzhouseUserRoleDept> data;
    private List<XzhouseDeptOrg> orgidlist;

    private List<XzhouseRoles> subrolelist;
    public List<XzhouseUserRoleDept> getData() {
        return data;
    }

    public void setData(List<XzhouseUserRoleDept> data) {
        this.data = data;
    }

    public List<XzhouseDeptOrg> getOrgidlist() {
        return orgidlist;
    }

    public void setOrgidlist(List<XzhouseDeptOrg> orgidlist) {
        this.orgidlist = orgidlist;
    }

    public List<XzhouseRoles> getSubrolelist() {
        return subrolelist;
    }

    public void setSubrolelist(List<XzhouseRoles> subrolelist) {
        this.subrolelist = subrolelist;
    }
}
