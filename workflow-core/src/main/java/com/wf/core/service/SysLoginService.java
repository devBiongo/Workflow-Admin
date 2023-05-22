package com.wf.core.service;

import com.wf.core.common.constants.CacheConstants;
import com.wf.core.common.redis.RedisCache;
import com.wf.core.common.utils.JwtUtil;
import com.wf.core.model.security.LoginUser;
import com.wf.core.security.context.AuthenticationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录校验方法
 *
 * @author Joffrey
 */
@Service
public class SysLoginService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("密码错误！");
        }finally {
            AuthenticationContextHolder.clearContext();
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        redisCache.setCacheObject(CacheConstants.LOGIN_TOKEN_KEY + loginUser.getUsername(), loginUser);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", loginUser.getUser().getUserId());
        claims.put("username", loginUser.getUser().getUsername());
        return JwtUtil.createJWT(claims);
    }


    @Transactional
    public void doRegister(String username, String password) {}

}