package org.ph.ssm.ZJJGWeb.bean;

public class LoginMetaData {
    private String title;
    private String icon;
    private String [] permissions;
    private String badge;
    private String affix;

    public String getTitle(){return title;}
    public String getIcon(){return icon;}
    public String getBadge(){return badge;}
    public String [] getPermissions(){return permissions;}
    public String getAffix(){return  affix;};

    public void setTitle(String Title){this.title=Title;}
    public void setIcon(String Icon){this.icon=Icon;}
    public void setBadge(String Badge){this.badge=Badge;}
    public void setPermissions(String [] Permissions){this.permissions=Permissions;}
    public void setAffix(String Affix){this.affix=Affix;}
    public LoginMetaData(){}

}
