package org.ph.ssm.ZJJGWeb.service;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransGjinfo;
import org.ph.ssm.ZJJGWeb.dao.AccCollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
}
