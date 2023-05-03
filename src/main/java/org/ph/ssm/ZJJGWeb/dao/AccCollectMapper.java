package org.ph.ssm.ZJJGWeb.dao;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransGjinfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccCollectMapper {
    List<XzhouseTransGjinfo> SelectGjinfoByDate(String DataDate);
    List<XzhouseTransGjinfo> SelectGjinfoByOrgID(String OrgID);
}
