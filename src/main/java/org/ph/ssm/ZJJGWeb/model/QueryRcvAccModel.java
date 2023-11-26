package org.ph.ssm.ZJJGWeb.model;
import org.ph.ssm.ZJJGWeb.model.BaseModel;
import org.ph.ssm.ZJJGWeb.bean.XzhouseParaZfacc;
import java.util.List;

public class QueryRcvAccModel extends  BaseModel{
    private List<XzhouseParaZfacc> data;

    public void setData(List<XzhouseParaZfacc> data) {
        this.data = data;
    }

    public List<XzhouseParaZfacc> getData() {
        return data;
    }
}
