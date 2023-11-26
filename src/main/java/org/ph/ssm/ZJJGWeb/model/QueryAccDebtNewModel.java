package org.ph.ssm.ZJJGWeb.model;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransZjjz;
import org.ph.ssm.ZJJGWeb.model.QueryBaseModel;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransZjjzNew;
import java.util.List;
import java.util.Map;
import org.ph.ssm.ZJJGWeb.bean.XzhouseDeptOrg;

public class QueryAccDebtNewModel extends QueryBaseModel {
    private List<XzhouseTransZjjzNew> data;
    private List<XzhouseDeptOrg> orgidlist;

    public List<XzhouseTransZjjzNew> getData() {
        return data;
    }

    public void setData(List<XzhouseTransZjjzNew> data) {
        this.data = data;
    }

    public List<XzhouseDeptOrg> getOrgidlist() {
        return orgidlist;
    }

    public void setOrgidlist(List<XzhouseDeptOrg> orgidlist) {
        this.orgidlist = orgidlist;
    }
}
