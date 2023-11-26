package org.ph.ssm.ZJJGWeb.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.ph.ssm.ZJJGWeb.bean.XzhouseGjJzValid;
import org.ph.ssm.ZJJGWeb.bean.XzhouseJgAccinfo;
import org.ph.ssm.ZJJGWeb.bean.XzhouseParaAccinfo;
import org.ph.ssm.ZJJGWeb.dao.AccInfoMapper;
//import org.ph.xzhouse.httpcomentity.DepositRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.ph.xzhouse.httpcomentity.AccountList;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class AccInfoService {
    @Autowired
    private AccInfoMapper accInfoMapper;

    public List<XzhouseParaAccinfo> getAccInfoByOrgID(String OrgID)
    {
        return accInfoMapper.SelectAccinfoByOrgID(OrgID);
    }

    public List<XzhouseParaAccinfo> getAccInfoByOrgIDList(List<String> OrgIDList)
    {
        return accInfoMapper.SelectAccinfoByOrgIDList(OrgIDList);
    }

    public List<XzhouseParaAccinfo> getAccInfoByCorpInfo(Map map)
    {
        return accInfoMapper.SelectAccinfoByCorpInfo(map);
    }
/*
    public List<XzhouseJgAccinfo> GetJgAccount(List<XzhouseParaAccinfo> xzhouseParaAccinfoList) throws JsonProcessingException {
        List<XzhouseJgAccinfo> xzhouseJgAccinfos=new ArrayList<XzhouseJgAccinfo>();
        for (XzhouseParaAccinfo xzhouseParaAccinfo: xzhouseParaAccinfoList
        ) {
            XzhouseJgAccinfo xzhouseJgAccinfo=new XzhouseJgAccinfo();

            String jGAccount=xzhouseParaAccinfo.getJgaccount();
            Map ReturnMap=httpCommService.QueryAccountList(null,null,jGAccount,null);
            List<AccountList> accountLists=(List<AccountList>)ReturnMap.get("Data");
            String Code=ReturnMap.get("Code").toString();
            if(Code.equals("000"))
            {
                xzhouseJgAccinfo.setZhmc(accountLists.get(0).getZhmc());
                xzhouseJgAccinfo.setXybh(xzhouseParaAccinfo.getContractno());
                xzhouseJgAccinfo.setCorpCode(accountLists.get(0).getCorpCode());
                xzhouseJgAccinfo.setCorpName(accountLists.get(0).getCorpName());
                xzhouseJgAccinfo.setItemName(accountLists.get(0).getItemName());
                xzhouseJgAccinfo.setBankNo(xzhouseParaAccinfo.getOrgid());
                xzhouseJgAccinfo.setBankName(xzhouseParaAccinfo.getOrgname());
                xzhouseJgAccinfo.setShortName(xzhouseParaAccinfo.getOrgname());
                xzhouseJgAccinfo.setJgAccount(accountLists.get(0).getJgAccount());
                xzhouseJgAccinfo.setShbh(accountLists.get(0).getShbh());
                xzhouseJgAccinfo.setCreateDate(accountLists.get(0).getCreateDate());
                xzhouseJgAccinfo.setUseFlag(accountLists.get(0).getUseFlag());
            }
            else
            {
                xzhouseJgAccinfo.setZhmc("");
                xzhouseJgAccinfo.setXybh("");
                xzhouseJgAccinfo.setCorpCode("");
                xzhouseJgAccinfo.setCorpName("");
                xzhouseJgAccinfo.setItemName("");
                xzhouseJgAccinfo.setBankNo("");
                xzhouseJgAccinfo.setBankName("");
                xzhouseJgAccinfo.setShortName("");
                xzhouseJgAccinfo.setJgAccount("");
                xzhouseJgAccinfo.setShbh("");
                xzhouseJgAccinfo.setCreateDate("");
                xzhouseJgAccinfo.setUseFlag("");
            }
            xzhouseJgAccinfos.add(xzhouseJgAccinfo);
        }
        return xzhouseJgAccinfos.stream().sorted(Comparator.comparing(XzhouseJgAccinfo::getUseFlag)).collect(Collectors.toList());
        //return xzhouseGjJzValidList;
    }*/
}
