package org.ph.ssm.ZJJGWeb.model;
import org.ph.ssm.ZJJGWeb.model.BaseModel;
import org.ph.ssm.ZJJGWeb.bean.XzhouseParaAccinfo;
import java.util.List;

public class QueryAccInfoModel extends BaseModel{
    private List<XzhouseParaAccinfo> data;

    public void setData(List<XzhouseParaAccinfo> data) {
        this.data = data;
    }

    public List<XzhouseParaAccinfo> getData() {
        return data;
    }
}
