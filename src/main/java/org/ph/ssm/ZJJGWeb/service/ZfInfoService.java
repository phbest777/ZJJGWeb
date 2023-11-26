package org.ph.ssm.ZJJGWeb.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.ph.ssm.ZJJGWeb.bean.XzhouseParaZfacc;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransZfinfo;
import org.ph.ssm.ZJJGWeb.dao.ZfInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ZfInfoService {
    @Autowired
    private ZfInfoMapper zfInfoMapper;


    public List<XzhouseParaZfacc> getZfAccInfoByAll()
    {
        return zfInfoMapper.SelectZfAccInfoByAll();
    }

    public List<XzhouseTransZfinfo> getZfTransByAll(Map FlagMap){return zfInfoMapper.SelectZfTransByAll(FlagMap);}
    public List<XzhouseTransZfinfo> getZfTransByMultiChoose(Map ChooseMap){return zfInfoMapper.SelectZfTransByMultiChoose(ChooseMap);}

    public Boolean UpdateZfInfo(Map UpdateMap)
    {
        return zfInfoMapper.updateZfInfoByIsAuth(UpdateMap);
    }
    public int GetZfTransPayed(){return zfInfoMapper.SelectZfTransPayed();}

    public int GetZfTransAuthed(){return zfInfoMapper.SelectZfTransAuthed();}
    public int GetZfTransOrigin(){return zfInfoMapper.SelectZfTransOrigin();}
}
