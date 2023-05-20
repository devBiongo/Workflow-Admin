package com.wf.core.security.handler;

import com.alibaba.fastjson2.JSON;
import com.wf.core.common.constants.HttpStatus;
import com.wf.core.model.response.AjaxResult;
import com.wf.core.common.utils.ServletUtils;
import com.wf.core.common.utils.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String msg = StringUtils.format("请求访问：{}，您的权限不足，请获取更高级别的访问权限。", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.FORBIDDEN, msg)));
    }
}