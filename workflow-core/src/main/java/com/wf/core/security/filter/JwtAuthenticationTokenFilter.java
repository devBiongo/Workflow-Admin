package com.wf.core.security.filter;

import java.io.IOException;

import com.wf.core.common.constants.CacheConstants;
import com.wf.core.common.redis.RedisCache;
import com.wf.core.common.utils.JwtUtil;
import com.wf.core.common.utils.StringUtil;
import com.wf.core.model.security.LoginUser;
import com.wf.core.security.context.AuthenticationContextHolder;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token过滤器
 *
 * @author Joffrey
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String jwt = JwtUtil.extractJwt(request);
        if (StringUtil.isNotEmpty(jwt)) {
            LoginUser loginUser = null;
            try {
                Claims claims = JwtUtil.parseJWT(jwt);
                loginUser = redisCache.getCacheObject(CacheConstants.LOGIN_TOKEN_KEY + claims.getId());
            } catch (Exception e) {
                AuthenticationContextHolder.clearContext();
                throw new RuntimeException("认证失败");
            }
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}