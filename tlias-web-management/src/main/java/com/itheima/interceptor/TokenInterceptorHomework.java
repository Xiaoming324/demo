package com.itheima.interceptor;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class TokenInterceptorHomework implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       /* String requestURI = request.getRequestURI();

        if (requestURI.contains("/login")) {
            log.info("login请求，放行");
            return true;
        }*/

        String token = request.getHeader("token");

        if (token == null || token.isEmpty()) {
            log.info("缺少token，不放行");
            response.setStatus(401);
            return false;
        }

        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("toke解析错误，不放行");
            response.setStatus(401);
            return false;
        }

        log.info("token解析成功，放行");
        return true;
    }
}
