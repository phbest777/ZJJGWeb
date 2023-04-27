package org.ph.ssm.ZJJGWeb.model;

import cn.hutool.core.lang.tree.Tree;


import java.util.List;

public class LoginMenuModel extends BaseModel {

    private List<Tree<String>> data;

    public List<Tree<String>> getData() {
        return data;
    }

    public void setData(List<Tree<String>> data) {
        this.data = data;
    }
}
