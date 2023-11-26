package org.ph.ssm.ZJJGWeb.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.ph.ssm.ZJJGWeb.bean.XzhouseGjJzValid;
import org.ph.ssm.ZJJGWeb.bean.XzhouseParaAccinfo;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransGjinfo;
import org.ph.ssm.ZJJGWeb.bean.XzhouseAddgjrecord;
import org.ph.ssm.ZJJGWeb.dao.AccCollectMapper;
//import org.ph.xzhouse.httpcomentity.DepositRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccCollectService {

    @Autowired
    private AccCollectMapper accCollectMapper;


    public List<XzhouseTransGjinfo> getGjinfoListByDate(String Date)
    {
        return accCollectMapper.SelectGjinfoByDate(Date);
    }

    public List<XzhouseTransGjinfo> getGjinfoListByOrgID(String OrgID)
    {
        return accCollectMapper.SelectGjinfoByOrgID(OrgID);
    }

    public List<XzhouseTransGjinfo> getGjinfoListByMultiChoose(Map ChooseMap)
    {
        return accCollectMapper.SelectGjinfoByMultiChoose(ChooseMap);
    }

    public List<XzhouseTransGjinfo> getGjinfoListByOrgIDS(List<String>OrgIDList)
    {
        return accCollectMapper.SelectGjinfoByOrgIDS(OrgIDList);
    }

    public List<XzhouseTransGjinfo> getGjinfoListByMultiChooseWithOrgIDS(Map ChooseMap)
    {
        return accCollectMapper.SelectGjinfoByMultiChooseWithOrgIDS(ChooseMap);
    }
    public Boolean AddGjRecord(XzhouseAddgjrecord xzhouseAddgjrecord)
    {
        return accCollectMapper.insertAddGjInfo(xzhouseAddgjrecord);
    }

    public Boolean UpdateGjInfo(Map UpdateMap)
    {
        return accCollectMapper.updateGjInfoByIsGj(UpdateMap);
    }

    public Boolean AddTransGjInfo(XzhouseTransGjinfo xzhouseTransGjinfo)
    {
        return accCollectMapper.insertTransGjInfo(xzhouseTransGjinfo);
    }
/*
    public List<XzhouseGjJzValid> GetGjJzValid(List<XzhouseParaAccinfo> xzhouseParaAccinfoList) throws JsonProcessingException {
        List<XzhouseGjJzValid> xzhouseGjJzValidList=new ArrayList<XzhouseGjJzValid>();
        for (XzhouseParaAccinfo xzhouseParaAccinfo: xzhouseParaAccinfoList
        ) {
            XzhouseGjJzValid GjJzValidRecord=new XzhouseGjJzValid();
            String Contractno= xzhouseParaAccinfo.getContractno();
            GjJzValidRecord.setPayeracc(xzhouseParaAccinfo.getJgaccount());
            GjJzValidRecord.setPayername(xzhouseParaAccinfo.getJgname());
            GjJzValidRecord.setContractno(Contractno);
            GjJzValidRecord.setOrgid(xzhouseParaAccinfo.getOrgid());
            GjJzValidRecord.setOrgname(xzhouseParaAccinfo.getOrgname());
            Date SysDate=new Date();
            SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
            GjJzValidRecord.setDatadate(formatter.format(SysDate));
            Map ReturnMap=httpCommService.QueryDepositInfo(Contractno);
            List<DepositRecord> depositRecords=null;
            String Code=ReturnMap.get("Code").toString();
            Double tempgjamt=0.00;
            Double tempjzamt=0.00;
            Double gjjzminus=0.00;
            if(Code.equals("000"))
            {
                depositRecords=(List<DepositRecord>) ReturnMap.get("Data");
                tempgjamt=Double.parseDouble(depositRecords.get(0).getZhzgj());
                tempjzamt=Double.parseDouble(depositRecords.get(0).getCkzje());
                gjjzminus=tempgjamt-tempjzamt;
                GjJzValidRecord.setGjamt(depositRecords.get(0).getZhzgj());
                GjJzValidRecord.setJzamt(depositRecords.get(0).getCkzje());
                GjJzValidRecord.setMinuamt(gjjzminus.toString());
                if(gjjzminus.equals(0.00))
                {
                    GjJzValidRecord.setIsvalid("1");
                }
                else
                {
                    GjJzValidRecord.setIsvalid("0");
                }
                xzhouseGjJzValidList.add(GjJzValidRecord);
            }
            else
            {
                GjJzValidRecord.setGjamt(tempgjamt.toString());
                GjJzValidRecord.setJzamt(tempjzamt.toString());
                GjJzValidRecord.setMinuamt(gjjzminus.toString());
                GjJzValidRecord.setIsvalid("-1");
            }

        }
        return xzhouseGjJzValidList.stream().sorted(Comparator.comparing(XzhouseGjJzValid::getIsvalid)).collect(Collectors.toList());
        //return xzhouseGjJzValidList;
    }*/
}
