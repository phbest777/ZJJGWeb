package org.ph.ssm.ZJJGWeb.dao;
import org.apache.ibatis.annotations.Param;
import org.ph.ssm.ZJJGWeb.bean.XzhouseParaZfacc;
import org.ph.ssm.ZJJGWeb.bean.XzhouseTransZfinfo;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

public interface ZfInfoMapper {

    List<XzhouseParaZfacc> SelectZfAccInfoByAll();
    List<XzhouseTransZfinfo> SelectZfTransByAll(Map FlagMap);
    List<XzhouseTransZfinfo> SelectZfTransByMultiChoose(Map ChooseMap);

    Boolean updateZfInfoByIsAuth(Map updateMap);

    Integer SelectZfTransPayed();

    Integer SelectZfTransAuthed();
    Integer SelectZfTransOrigin();
}

