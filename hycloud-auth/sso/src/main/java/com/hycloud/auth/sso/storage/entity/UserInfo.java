package com.hycloud.auth.sso.storage.entity;

public class UserInfo {
    private String username;

    private String nickname;

    private String avantar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvantar() {
        return avantar;
    }

    public void setAvantar(String avantar) {
        this.avantar = avantar;
    }
}