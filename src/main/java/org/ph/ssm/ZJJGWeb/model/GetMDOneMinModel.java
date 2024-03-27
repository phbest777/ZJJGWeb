package org.ph.ssm.ZJJGWeb.model;
import org.ph.ssm.ZJJGWeb.bean.QuantFutureMdOnemin;
import java.util.List;
import java.util.Map;

public class GetMDOneMinModel extends BaseModel{
    public List<QuantFutureMdOnemin> getData() {
        return data;
    }

    public void setData(List<QuantFutureMdOnemin> data) {
        this.data = data;
    }

    private List<QuantFutureMdOnemin> data;
}
