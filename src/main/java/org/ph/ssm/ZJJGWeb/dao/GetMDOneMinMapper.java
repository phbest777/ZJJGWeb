package org.ph.ssm.ZJJGWeb.dao;
import java.util.List;
import java.util.Map;
import org.ph.ssm.ZJJGWeb.bean.QuantFutureMdOnemin;

public interface GetMDOneMinMapper {
    List<QuantFutureMdOnemin> SelectMDOneminByCode(Map selectmap);
    List<QuantFutureMdOnemin> SelectMDOneminByMin(Map selectmap);
}
