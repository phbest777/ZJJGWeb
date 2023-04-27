package org.ph.ssm.ZJJGWeb.bean;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.jdbc.Null;
import org.ph.ssm.ZJJGWeb.bean.LoginChildrenData;
import org.ph.ssm.ZJJGWeb.bean.LoginMetaData;
import org.ph.ssm.ZJJGWeb.bean.LoginMetaData;
import org.ph.ssm.ZJJGWeb.bean.XzhouseMenu;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LoginReturnData {

    private List<Tree<String>> data;

    public List<Tree<String>> getData() {
        return data;
    }

    public void setData(List<Tree<String>> data) {
        this.data = data;
    }
    public LoginReturnData(){};



    public LoginReturnData(List<XzhouseMenu> MenuList){
       /* List<LoginChildrenData> ChildrenList=new ArrayList<LoginChildrenData>();
        for (final XzhouseMenu menu:MenuList
             ) {
            LoginChildrenData Children=new LoginChildrenData();
            if(menu.getMenuLevel().equals("1")) {
                Children.setPath(menu.getMenuPath());
                Children.setComponent(menu.getMenuComponet());
                Children.setRedirect(menu.getMenuRedirect());
                Children.setName(menu.getMenuName());
                ChildrenList.add(Children);
            }
            if(menu.getMenuLevel().equals("2")||menu.getMenuLevel().equals("3")){
                List<LoginChildrenData> subChildrenList=new ArrayList<LoginChildrenData>();
               //XzhouseMenu tmepmenu= MenuList.stream().filter(menuItem->menu.getMenuParentCode().equals(menuItem.getMenuCode())).findFirst();
               //Optional<XzhouseMenu> tempMenu=MenuList.stream().filter(a -> "xxx".equals(a.getMenuCode())).findFirst();
                ///找到上一级菜单的实体
                Optional<XzhouseMenu> tempMenu= MenuList.stream().filter(menuItem->menu.getMenuParentCode().equals(menuItem.getMenuCode())).findFirst();
                ///用上一级菜单的menuname查找父节点
                Optional<LoginChildrenData> upchild= Optional.empty();
                if(menu.getMenuLevel().equals("2")){
                    upchild=ChildrenList.stream().filter(Item->tempMenu.get().getMenuName().equals(Item.getName())).findFirst();
                }
                else {
                    upchild = subChildrenList.stream().filter(Item -> tempMenu.get().getMenuName().equals(Item.getName())).findFirst();
                }
                LoginChildrenData subChildren=new LoginChildrenData();
                subChildren.setPath(menu.getMenuPath());
                subChildren.setName(menu.getMenuName());
                subChildren.setComponent(menu.getMenuComponet());
                LoginMetaData subMeta=new LoginMetaData();
                subMeta.setIcon(menu.getMenuIcon());
                subMeta.setTitle(menu.getMenuTitle());
                if(!menu.getMenuAffix().isEmpty())
                {
                    System.out.println(menu.getMenuAffix());

                }
                subMeta.setAffix(menu.getMenuAffix());
                subChildren.setMeta(subMeta);
                //将子节点加入到子节点列表
                subChildrenList.add(subChildren);
                upchild.get().setChildren(subChildrenList);//将子节点列表加入到父节点

            }
        }*/

    TreeNodeConfig config = new TreeNodeConfig();
    config.setIdKey("menuCode");
    config.setParentIdKey("menuParentCode");
    List<Tree<String>> treeList = TreeUtil.build(MenuList, "00000000", config, ((object, treeNode) -> {
        //对key进行映射赋值
        treeNode.putExtra("menuCode", object.getMenuCode());
        treeNode.putExtra("menuParentCode", object.getMenuParentCode());
        treeNode.putExtra("path", object.getMenuPath());
        treeNode.putExtra("name", object.getMenuName());
        treeNode.putExtra("component", object.getMenuComponet());
        if(object.getMenuRedirect()!=null)
        {
           treeNode.putExtra("redirect",object.getMenuRedirect());
        }
        LoginMetaData tempMetaData=new LoginMetaData();
        if(object.getMenuPermiss()!=null)
        {
            tempMetaData.setPermissions(object.getMenuPermiss().split(","));
        }
        if(object.getMenuTitle()!=null) {
            tempMetaData.setTitle(object.getMenuTitle());
        }
        if(object.getMenuIcon()!=null) {
            tempMetaData.setIcon(object.getMenuIcon());
        }
        if(object.getMenuAffix()!=null)
        {
            tempMetaData.setAffix(object.getMenuAffix());
        }
        if(object.getMenuPermiss()!=null||object.getMenuTitle()!=null||object.getMenuIcon()!=null||object.getMenuAffix()!=null)
        {
            treeNode.putExtra("meta",tempMetaData);
        }

    }));
        this.data=treeList;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonStr = objectMapper.writeValueAsString(treeList);
            //System.out.print(jsonStr);
        }
        catch (Exception ex)
        {
            System.out.print(ex.toString());
        }

    }

}
