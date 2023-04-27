package org.ph.ssm.ZJJGWeb.dao;
import org.ph.ssm.ZJJGWeb.bean.XzhouseMenu;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.ph.ssm.ZJJGWeb.bean.XzhouseUsers;

public interface LoginMapper {

    List<XzhouseMenu> SelectMenuList(String UserCode);
    List<XzhouseUsers> SelectUsers(String UserCode);
}
