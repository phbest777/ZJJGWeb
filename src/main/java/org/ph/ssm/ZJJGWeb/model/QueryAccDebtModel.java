package org.ph.ssm.ZJJGWeb.model;
import org.ph.ssm.ZJJGWeb.model.QueryBaseModel;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransZjjz;
import java.util.List;
import java.util.Map;
import org.ph.ssm.ZJJGWeb.bean.XzhouseDeptOrg;

public class QueryAccDebtModel extends QueryBaseModel{
    private List<XzhouseTransZjjz> data;
    private List<XzhouseDeptOrg> orgidlist;

    public List<XzhouseTransZjjz> getData() {
        return data;
    }

    public void setData(List<XzhouseTransZjjz> data) {
        this.data = data;
    }

    public List<XzhouseDeptOrg> getOrgidlist() {
        return orgidlist;
    }

    public void setOrgidlist(List<XzhouseDeptOrg> orgidlist) {
        this.orgidlist = orgidlist;
    }
}
