package org.ph.ssm.ZJJGWeb.bean;
import org.ph.ssm.ZJJGWeb.bean.LoginMetaData;
import java.util.ArrayList;
import java.util.List;
public class LoginChildrenData {
    private String path;
    private String name;
    private String component;
    private String redirect;
    private String alwaysShow;
    private LoginMetaData meta;

    private List<LoginChildrenData> children;

    public String getPath(){return path;}
    public String getName(){return name;}
    public String getComponent(){return component;}
    public String getRedirect(){return redirect;}
    public String getAlwaysShow(){return alwaysShow;}
    public LoginMetaData getMeta(){return meta;}
    public List<LoginChildrenData> getChildren(){return children;}

    public void setPath(String Path){this.path=Path;}
    public void setName(String Name){this.name=Name;}
    public void setComponent(String Component){this.component=Component;}
    public void setRedirect(String Redirect){this.redirect=Redirect;}
    public void setAlwaysShow(String AlwaysShow){this.alwaysShow=AlwaysShow;}
    public void setMeta(LoginMetaData Meta){this.meta=Meta;}
    public void setChildren(List<LoginChildrenData> Children){this.children=Children;}

    public LoginChildrenData(){}
    public LoginChildrenData(String Path,String Name,String Component,String Redirect,String AlwaysShow,LoginMetaData Meta)
    {
        this.path=Path;
        this.name=Name;
        this.component=Component;
        this.redirect=Redirect;
        this.alwaysShow=AlwaysShow;
        this.meta=Meta;
    }
    public LoginChildrenData(String Path,String Name,String Component,String Redirect,String AlwaysShow,LoginMetaData Meta,List<LoginChildrenData> Children)
    {
        this.path=Path;
        this.name=Name;
        this.component=Component;
        this.redirect=Redirect;
        this.alwaysShow=AlwaysShow;
        this.meta=Meta;
        this.children=Children;
    }
}
