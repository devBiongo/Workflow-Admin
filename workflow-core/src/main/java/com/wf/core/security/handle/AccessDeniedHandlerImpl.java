package com.wf.core.security.handle;

import com.wf.core.constants.HttpStatus;
import com.wf.core.model.AjaxResult;
import com.wf.core.utils.ServletUtils;
import com.wf.core.utils.StringUtils;
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
        // 自定义访问被拒绝的处理逻辑
        // ...

        // 设置返回给客户端的响应状态码和消息
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("Access Denied");

        int code = HttpStatus.UNAUTHORIZED;
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
//        ServletUtils.renderString(response, com.alibaba.fastjson2.JSON.toJSONString(AjaxResult.error(code, msg)));
    }
}