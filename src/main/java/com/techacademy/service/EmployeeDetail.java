package com.techacademy.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Authentication;

public class EmployeeDetail implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final Employee employee;
    private final Authentication authentication;
    private final Collection<? extends GrantedAuthority> authorities;

    public EmployeeDetail(Employee employee, Authentication authentication) {
        this.employee = employee;
        this.authentication = authentication;
        this.authorities = new ArrayList<GrantedAuthority>();
    }

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return authentication.getPassword();
    }

    @Override
    public String getUsername() {
        return authentication.getCode();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 従業員が期限切れでなければtrueを返す
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 従業員がロックされていなければtrueを返す
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 従業員のパスワードが期限切れでなければtrueを返す
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 従業員が有効であればtrueを返す
        return true;
    }
}
