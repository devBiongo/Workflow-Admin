package com.wf.core.security.handler;

import com.alibaba.fastjson2.JSON;
import com.wf.core.common.constants.HttpStatus;
import com.wf.core.model.response.AjaxResult;
import com.wf.core.common.utils.ServletUtils;
import com.wf.core.common.utils.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e){
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.UNAUTHORIZED, msg)));
    }
}
