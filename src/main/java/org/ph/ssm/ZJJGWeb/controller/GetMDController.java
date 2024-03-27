package org.ph.ssm.ZJJGWeb.controller;
import org.ph.ssm.ZJJGWeb.model.GetMDOneMinModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.JsonNode;
import org.ph.ssm.ZJJGWeb.bean.QuantFutureMdOnemin;
import org.ph.ssm.ZJJGWeb.service.GetMDService;

@RestController
public class GetMDController {
    @Autowired
    private GetMDService getMDService;

    @CrossOrigin
    @PostMapping(value = "/Future/MD/GetOneMinuteMDByCode")
    public GetMDOneMinModel GetOneMinuteMDByCode(@RequestBody JsonNode QueryInfo)
    {
        String UserCode=QueryInfo.path("username").asText();
        String InstrumentID=QueryInfo.path("instrumentid").asText();
        String WorkDay=QueryInfo.path("workday").asText();
        String UpdateMin=QueryInfo.path("updatemin").asText();
        int Flag=QueryInfo.path("flag").asInt();
        Map SelectMap=new HashMap();
        SelectMap.put("InstrumentID",InstrumentID);
        SelectMap.put("TradingDay",WorkDay);
        SelectMap.put("UpdateMin",UpdateMin);
        List<QuantFutureMdOnemin> quantFutureMdOneminList=null;
        if(Flag==0)
        {
                quantFutureMdOneminList = getMDService.GetMDOneminByCode(SelectMap);
                System.out.println("Future MD total is:"+quantFutureMdOneminList.size());
        }
        else
        {
            quantFutureMdOneminList=getMDService.GetMDOneminByMin(SelectMap);
            System.out.println("Future MD total is:"+quantFutureMdOneminList.size());
        }
        GetMDOneMinModel getMDOneMinModel=new GetMDOneMinModel();
        getMDOneMinModel.setCode("200");
        getMDOneMinModel.setMsg("查询行情成功");
        getMDOneMinModel.setData(quantFutureMdOneminList);
        return getMDOneMinModel;
    }
}
