package org.ph.ssm.ZJJGWeb.model;
import java.util.List;
import org.ph.ssm.ZJJGWeb.bean.ReturnDataUserInfo;

public class LoginUserInfoModel extends BaseModel{
    private ReturnDataUserInfo data;

    public ReturnDataUserInfo getData() {
        return data;
    }

    public void setData(ReturnDataUserInfo data) {
        this.data = data;
    }
}
