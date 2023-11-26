package org.ph.ssm.ZJJGWeb.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransZjjz;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransZjjzNew;
import org.ph.ssm.ZJJGWeb.bean.XzhouseParaAccinfo;
import org.ph.ssm.ZJJGWeb.bean.XzhouseMoneyaccount;
import org.ph.ssm.ZJJGWeb.dao.AccDebtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccDebtService {
    @Autowired AccDebtMapper accDebtMapper;

    public List<XzhouseTransZjjz> getJzinfoByOrgIDS(List<String>OrgIDList)
    {
        return accDebtMapper.SelectJzinfoByOrgIDS(OrgIDList);
    }
    public List<XzhouseTransZjjzNew> getJzinfoNewByOrgIDS(List<String>OrgIDList)
    {
        return accDebtMapper.SelectJzinfoNewByOrgIDS(OrgIDList);
    }
    public List<XzhouseTransZjjz> getJzinfoByMultiChooseWithOrgIDS(Map map)
    {
        return accDebtMapper.SelectJzinfoByMultiChooseWithOrgIDS(map);
    }
    public List<XzhouseTransZjjzNew> getJzinfoNewByMultiChooseWithOrgIDS(Map map)
    {
        return accDebtMapper.SelectJzinfoNewByMultiChooseWithOrgIDS(map);
    }
    public List<XzhouseTransZjjz> getJzinfoByMultiChoose(Map map)
    {
        return accDebtMapper.SelectJzinfoByMultiChoose(map);
    }

    public List<XzhouseTransZjjzNew> getJzinfoNewByMultiChoose(Map map)
    {
        return accDebtMapper.SelectJzinfoNewByMultiChoose(map);
    }
    public Boolean UpdateJzInfo(Map UpdateMap){
        return accDebtMapper.updateJzInfoByIsJz(UpdateMap);
    }
    public Boolean UpdateJzInfoNew(Map UpdateMap){
        return accDebtMapper.updateJzInfoNewByIsJz(UpdateMap);
    }
    public Boolean AddMoneyAccount(XzhouseMoneyaccount xzhouseMoneyaccount)
    {
        return accDebtMapper.insertMoneyAccountInfo(xzhouseMoneyaccount);
    }
    public Boolean AddTransZjjz(XzhouseTransZjjz xzhouseTransZjjz){
        return accDebtMapper.insertTransJzInfo(xzhouseTransZjjz);
    }
    public Boolean AddTransZjjzNew(XzhouseTransZjjzNew xzhouseTransZjjzNew){
        return accDebtMapper.insertTransJzInfoNew(xzhouseTransZjjzNew);
    }

}
