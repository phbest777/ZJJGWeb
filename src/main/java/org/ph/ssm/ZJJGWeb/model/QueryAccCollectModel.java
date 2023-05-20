package org.ph.ssm.ZJJGWeb.model;
import org.ph.ssm.ZJJGWeb.model.QueryBaseModel;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransGjinfo;
import java.util.List;
import java.util.Map;
import org.ph.ssm.ZJJGWeb.bean.XzhouseDeptOrg;

public class QueryAccCollectModel extends QueryBaseModel{
    private List<XzhouseTransGjinfo> data;
    private List<XzhouseDeptOrg> orgidlist;

    public List<XzhouseTransGjinfo> getData() {
        return data;
    }

    public void setData(List<XzhouseTransGjinfo> data) {
        this.data = data;
    }

    public List<XzhouseDeptOrg> getOrgidlist() {
        return orgidlist;
    }

    public void setOrgidlist(List<XzhouseDeptOrg> orgidlist) {
        this.orgidlist = orgidlist;
    }
}
