package org.ph.ssm.ZJJGWeb.dao;
import org.apache.ibatis.annotations.Param;
import org.ph.ssm.ZJJGWeb.bean.XzhouseParaAccinfo;
import java.util.List;
import java.util.Map;
public interface AccInfoMapper {

    List<XzhouseParaAccinfo> SelectAccinfoByOrgID(String OrgID);
    List<XzhouseParaAccinfo> SelectAccinfoByOrgIDList(@Param("OrgIDList") List<String> OrgIDList);
}
