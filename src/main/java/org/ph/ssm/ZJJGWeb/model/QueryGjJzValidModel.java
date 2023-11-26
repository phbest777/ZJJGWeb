package org.ph.ssm.ZJJGWeb.model;
import org.ph.ssm.ZJJGWeb.bean.XzhouseGjJzValid;
import java.util.List;
import org.ph.ssm.ZJJGWeb.bean.XzhouseDeptOrg;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransGjinfo;

public class QueryGjJzValidModel extends QueryBaseModel{
    private List<XzhouseGjJzValid> data;
    private List<XzhouseDeptOrg> orgidlist;

    public List<XzhouseGjJzValid> getData() {
        return data;
    }

    public void setData(List<XzhouseGjJzValid> data) {
        this.data = data;
    }

    public List<XzhouseDeptOrg> getOrgidlist() {
        return orgidlist;
    }

    public void setOrgidlist(List<XzhouseDeptOrg> orgidlist) {
        this.orgidlist = orgidlist;
    }
}
