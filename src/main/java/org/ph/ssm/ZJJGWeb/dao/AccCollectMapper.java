package org.ph.ssm.ZJJGWeb.dao;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransGjinfo;
import org.ph.ssm.ZJJGWeb.bean.XzhouseAddgjrecord;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AccCollectMapper {
    List<XzhouseTransGjinfo> SelectGjinfoByDate(String DataDate);
    List<XzhouseTransGjinfo> SelectGjinfoByOrgID(String OrgID);

    List<XzhouseTransGjinfo> SelectGjinfoByMultiChoose(Map ChooseMap);
    List<XzhouseTransGjinfo> SelectGjinfoByOrgIDS(@Param("OrgIDList") List<String> OrgIDList);
    List<XzhouseTransGjinfo> SelectGjinfoByMultiChooseWithOrgIDS(Map ChooseMap);
    Boolean insertAddGjInfo(XzhouseAddgjrecord xzhouseAddgjrecord);
    Boolean updateGjInfoByIsGj(Map updateMap);
    Boolean insertTransGjInfo(XzhouseTransGjinfo xzhouseTransGjinfo);

}
