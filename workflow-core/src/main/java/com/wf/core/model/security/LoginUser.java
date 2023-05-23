package com.wf.core.model.security;

import com.alibaba.fastjson2.annotation.JSONField;
import com.wf.core.model.system.entity.SysUserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Set;

/**
 * 登录用户身份权限
 *
 * @author Joffrey
 */
public class LoginUser implements UserDetails {


    public LoginUser(SysUserEntity user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        Authorities = authorities;
    }

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识
     */
    private String myUuid;

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
    private Collection<? extends GrantedAuthority> Authorities;

    /**
     * 用户信息
     */
    private SysUserEntity user;

    @JSONField(serialize = false)
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.Authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> Authorities) {
        this.Authorities = Authorities;
    }

    public Long getUserId() {
        return user.getUserId();
    }

    public Long getDeptId() {
        return user.getDeptId();
    }

    public String getMyUuid() {
        return myUuid;
    }

    public void setMyUuid(String myUuid) {
        this.myUuid = myUuid;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public SysUserEntity getUser() {
        return user;
    }

    public void setUser(SysUserEntity user) {
        this.user = user;
    }
}
