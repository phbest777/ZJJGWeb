package org.ph.ssm.ZJJGWeb.service;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransGjinfo;
import org.ph.ssm.ZJJGWeb.dao.AccCollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
public class AccCollectService {

    @Autowired
    private AccCollectMapper accCollectMapper;

    public List<XzhouseTransGjinfo> getGjinfoListByDate(String Date)
    {
        return accCollectMapper.SelectGjinfoByDate(Date);
    }
}
