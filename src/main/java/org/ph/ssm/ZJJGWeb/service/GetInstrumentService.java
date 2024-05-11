package org.ph.ssm.ZJJGWeb.service;
import org.ph.ssm.ZJJGWeb.bean.QuantFutureMdOnemin;
import org.ph.ssm.ZJJGWeb.dao.GetMDOneMinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import org.ph.ssm.ZJJGWeb.dao.GetInstrumentMapper;
import org.ph.ssm.ZJJGWeb.bean.QuantFutureInstrument;
@Service

public class GetInstrumentService {
    @Autowired
    private GetInstrumentMapper getInstrumentMapper;

    public List<QuantFutureInstrument> GetInstrument()
    {
        return getInstrumentMapper.SelectInstrument();
    }
}
