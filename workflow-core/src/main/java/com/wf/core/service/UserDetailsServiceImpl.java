package com.wf.core.service;

import com.wf.core.model.system.entity.SysMenuEntity;
import com.wf.core.model.system.entity.SysUserEntity;
import com.wf.core.common.enums.UserStatus;
import com.wf.core.model.security.LoginUser;
import com.wf.core.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户验证处理
 *
 * @author Joffrey
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserEntity user = sysUserService.selectUserByUsername(username);
        if (StringUtil.isNull(user)) {
            log.info("ユーザー：{} 存在しない。", username);
            throw new RuntimeException("ユーザー：" + username + " 存在しない。");
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("ユーザー：{} 削除されました。", username);
            throw new RuntimeException("ユーザー：" + username + " 削除されました。");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("ユーザー：{} 無効化されました。", username);
            throw new RuntimeException("ユーザー：" + username + " 無効化されました。");
        }
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUserEntity user) {
        List<SysMenuEntity> menus = sysMenuService.selectMenuTreeByUserId(user.getUserId());
        Set<? extends GrantedAuthority> authorities = menus.stream().map(SysMenuEntity::getPerms)
                .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        return new LoginUser(user,authorities);
    }
}
