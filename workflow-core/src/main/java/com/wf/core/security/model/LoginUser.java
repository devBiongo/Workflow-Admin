package com.wf.core.security.model;

import com.alibaba.fastjson2.annotation.JSONField;
import com.wf.core.security.model.entity.SecurityUserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * 登录用户身份权限
 * 
 * @author ruoyi
 */
public class LoginUser implements UserDetails
{
    private static final long serialVersionUID = 1L;


    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 用户信息
     */
    private SecurityUserEntity user;


    public Set<String> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(Set<String> permissions)
    {
        this.permissions = permissions;
    }

    public SecurityUserEntity getUser()
    {
        return user;
    }

    public void setUser(SecurityUserEntity user)
    {
        this.user = user;
    }

    public LoginUser(SecurityUserEntity user, Set<String> permissions)
    {
        this.user = user;
        this.permissions = permissions;
    }


    @JSONField(serialize = false)
    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public String getUsername()
    {
        return user.getUsername();
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     * 
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     * 
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     * 
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }






}
