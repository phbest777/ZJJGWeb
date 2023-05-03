package org.ph.ssm.ZJJGWeb.model;
import org.ph.ssm.ZJJGWeb.model.QueryBaseModel;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransGjinfo;
import java.util.List;

public class QueryAccCollectModel extends QueryBaseModel{
    private List<XzhouseTransGjinfo> data;

    public List<XzhouseTransGjinfo> getData() {
        return data;
    }

    public void setData(List<XzhouseTransGjinfo> data) {
        this.data = data;
    }
}
