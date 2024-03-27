package org.ph.ssm.ZJJGWeb.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import org.ph.ssm.ZJJGWeb.dao.GetMDOneMinMapper;
import org.ph.ssm.ZJJGWeb.bean.QuantFutureMdOnemin;
@Service
public class GetMDService {
    @Autowired
    private GetMDOneMinMapper getMDOneMinMapper;


    public List<QuantFutureMdOnemin> GetMDOneminByCode(Map selectmap)
    {
        return getMDOneMinMapper.SelectMDOneminByCode(selectmap);
    }
    public List<QuantFutureMdOnemin> GetMDOneminByMin(Map selectmap)
    {
        return getMDOneMinMapper.SelectMDOneminByMin(selectmap);
    }
}
