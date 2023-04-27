package org.ph.ssm.ZJJGWeb.bean;

import java.util.List;

public class ReturnDataUserInfo {
    private List<String> permissions;
    private String username;
    private String avatar;

    public List<String> getPermissions() {
        return permissions;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
