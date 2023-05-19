package com.wf.core.security.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token过滤器
 *
 * @author BionGo
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        System.out.println("这里是卢本伟广场");
//        String token = JwtUtil.getJWT(request);
//        if (StringUtils.hasText(token)) {
//            LoginUser loginUser = null;
//            try {
//                Claims claims = JwtUtil.parseJWT(token);
//                loginUser = redisCache.getCacheObject(CacheConstant.LOGIN_KEY + claims.getId());
//            } catch (Exception e) {
//                AuthenticationContextHolder.clearContext();
//                String message = e.getMessage();
//                if(e instanceof ExpiredJwtException) message = "令牌已过期，请重新登录";
//                if(e instanceof MalformedJwtException||e instanceof SignatureException) message = "令牌非法";
//                authenticationEntryPoint.commence(request, response, new AuthenticationErrorException(message));
//                return;
//            }
//            UsernamePasswordAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
//            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        }
//        chain.doFilter(request, response);
    }
}