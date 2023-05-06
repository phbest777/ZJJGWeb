package org.ph.ssm.ZJJGWeb.dao;
import org.ph.ssm.ZJJGWeb.bean.XzhouseParaAccinfo;
import java.util.List;
import java.util.Map;
public interface AccInfoMapper {

    List<XzhouseParaAccinfo> SelectAccinfoByOrgID(String OrgID);
}
