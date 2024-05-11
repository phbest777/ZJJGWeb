package org.ph.ssm.ZJJGWeb.model;
import org.ph.ssm.ZJJGWeb.bean.QuantFutureInstrument;
import java.util.List;
import java.util.Map;

public class GetInstrumentModel extends BaseModel{
    public List<QuantFutureInstrument> getData() {
        return data;
    }

    public void setData(List<QuantFutureInstrument> data) {
        this.data = data;
    }

    private List<QuantFutureInstrument> data;
}
