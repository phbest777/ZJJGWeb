package org.ph.ssm.ZJJGWeb.model;
import org.ph.ssm.ZJJGWeb.bean.XzhouseJgAccinfo;
import java.util.List;
import org.ph.ssm.ZJJGWeb.bean.XzhouseDeptOrg;
public class QueryJgAccInfoModel extends QueryBaseModel{
    private List<XzhouseJgAccinfo> data;
    private List<XzhouseDeptOrg> orgidlist;

    public List<XzhouseJgAccinfo> getData() {
        return data;
    }

    public void setData(List<XzhouseJgAccinfo> data) {
        this.data = data;
    }

    public List<XzhouseDeptOrg> getOrgidlist() {
        return orgidlist;
    }

    public void setOrgidlist(List<XzhouseDeptOrg> orgidlist) {
        this.orgidlist = orgidlist;
    }
}
