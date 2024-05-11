package org.ph.ssm.ZJJGWeb.dao;
import java.util.List;
import java.util.Map;
import org.ph.ssm.ZJJGWeb.bean.QuantFutureInstrument;

public interface GetInstrumentMapper {
    List<QuantFutureInstrument> SelectInstrument();
}
