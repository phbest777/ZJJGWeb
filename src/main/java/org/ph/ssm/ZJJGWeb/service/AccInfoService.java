package org.ph.ssm.ZJJGWeb.service;
import org.ph.ssm.ZJJGWeb.bean.XzhouseParaAccinfo;
import org.ph.ssm.ZJJGWeb.dao.AccCollectMapper;
import org.ph.ssm.ZJJGWeb.dao.AccInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AccInfoService {
    @Autowired
    private AccInfoMapper accInfoMapper;
    public List<XzhouseParaAccinfo> getAccInfoByOrgID(String OrgID)
    {
        return accInfoMapper.SelectAccinfoByOrgID(OrgID);
    }
}
