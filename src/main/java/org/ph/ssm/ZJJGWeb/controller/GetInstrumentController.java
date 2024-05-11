package org.ph.ssm.ZJJGWeb.controller;
import org.ph.ssm.ZJJGWeb.model.GetInstrumentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.JsonNode;
import org.ph.ssm.ZJJGWeb.bean.QuantFutureInstrument;
import org.ph.ssm.ZJJGWeb.service.GetInstrumentService;

@RestController
public class GetInstrumentController {
    @Autowired
    private GetInstrumentService getInstrumentService;

    @CrossOrigin
    @PostMapping(value = "/Future/Instrument/GetInstrumentAll")
    public GetInstrumentModel GetInstrumentByAll(@RequestBody JsonNode QueryInfo)
    {
        String UserCode=QueryInfo.path("username").asText();
        List<QuantFutureInstrument> quantFutureInstrumentList=getInstrumentService.GetInstrument();
        System.out.println("查询所有合约数："+quantFutureInstrumentList.size()+"个");
        GetInstrumentModel getInstrumentModel=new GetInstrumentModel();
        getInstrumentModel.setCode("200");
        getInstrumentModel.setMsg("查询合约成功");
        getInstrumentModel.setData(quantFutureInstrumentList);
        return getInstrumentModel;
    }
}
