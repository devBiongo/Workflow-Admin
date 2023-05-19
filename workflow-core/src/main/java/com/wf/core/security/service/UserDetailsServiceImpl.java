package com.wf.core.security.service;

import com.wf.core.security.model.entity.SecurityUserEntity;
import com.wf.core.enums.UserStatus;
import com.wf.core.security.model.LoginUser;
import com.wf.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author Joffrey
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private SecurityUserService securityUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        SecurityUserEntity user = securityUserService.selectUserByUsername(username);
        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new RuntimeException("登录用户：" + username + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new RuntimeException("对不起，您的账号：" + username + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new RuntimeException("对不起，您的账号：" + username + " 已停用");
        }
        return createLoginUser(null);
    }

    public UserDetails createLoginUser(SecurityUserEntity user)
    {
        return new LoginUser(user, null);
    }
}