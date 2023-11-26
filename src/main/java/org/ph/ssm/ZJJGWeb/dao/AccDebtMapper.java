package org.ph.ssm.ZJJGWeb.dao;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransZjjz;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransZjjzNew;
import org.ph.ssm.ZJJGWeb.bean.XzhouseMoneyaccount;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AccDebtMapper {
    List<XzhouseTransZjjz> SelectJzinfoByOrgIDS(@Param("OrgIDList") List<String> OrgIDList);
    List<XzhouseTransZjjz> SelectJzinfoByMultiChooseWithOrgIDS(Map map);
    List<XzhouseTransZjjz> SelectJzinfoByMultiChoose(Map map);

    List<XzhouseTransZjjzNew> SelectJzinfoNewByOrgIDS(@Param("OrgIDList") List<String> OrgIDList);
    List<XzhouseTransZjjzNew> SelectJzinfoNewByMultiChoose(Map map);
    List<XzhouseTransZjjzNew> SelectJzinfoNewByMultiChooseWithOrgIDS(Map map);

    Boolean insertTransJzInfo(XzhouseTransZjjz xzhouseTransZjjz);

    Boolean insertTransJzInfoNew(XzhouseTransZjjzNew xzhouseTransZjjzNew);
    Boolean insertMoneyAccountInfo(XzhouseMoneyaccount xzhouseMoneyaccount);
    Boolean updateJzInfoByIsJz(Map map);
    Boolean updateJzInfoNewByIsJz(Map map);
}
