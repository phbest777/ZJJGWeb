package org.ph.ssm.ZJJGWeb.model;
import org.ph.ssm.ZJJGWeb.bean.XzhouseParaZfacc;
import org.ph.ssm.ZJJGWeb.model.BaseModel;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransZfinfo;
import java.util.List;

public class QueryZfTransModel extends QueryBaseModel{

    private List<XzhouseTransZfinfo> data;

    public void setData(List<XzhouseTransZfinfo> data) {
        this.data = data;
    }

    public List<XzhouseTransZfinfo> getData() {
        return data;
    }
}
