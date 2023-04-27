package org.ph.ssm.ZJJGWeb.model;
import java.util.List;
import java.util.Map;
import org.ph.ssm.ZJJGWeb.bean.ReturnDataLogin;


public class LoginModel extends BaseModel{

    private ReturnDataLogin data;

    public ReturnDataLogin getData() {
        return data;
    }

    public void setData(ReturnDataLogin data) {
        this.data = data;
    }
}

