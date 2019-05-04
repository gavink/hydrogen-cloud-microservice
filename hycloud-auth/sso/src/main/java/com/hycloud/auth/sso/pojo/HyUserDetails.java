package com.hycloud.auth.sso.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class HyUserDetails implements UserDetails {

    private String username;

    private String password;

    private Integer status;

    private List<SimpleGrantedAuthority> auths;

    public HyUserDetails(String username, String password, Integer status, List<SimpleGrantedAuthority> auths) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.auths = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return auths;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == 1;
    }
}
