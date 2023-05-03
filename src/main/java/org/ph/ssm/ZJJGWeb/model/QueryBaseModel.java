package org.ph.ssm.ZJJGWeb.model;
import org.ph.ssm.ZJJGWeb.model.BaseModel;
public class QueryBaseModel extends BaseModel{

    private long totalCount;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
