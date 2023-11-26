package org.ph.ssm.ZJJGWeb.model;
import org.ph.ssm.ZJJGWeb.bean.XzhouseUsers;
import java.util.List;

public class QueryUserInfoModel extends BaseModel{
    private XzhouseUsers data;

    public XzhouseUsers getData() {
        return data;
    }

    public void setData(XzhouseUsers data) {
        this.data = data;
    }
}
