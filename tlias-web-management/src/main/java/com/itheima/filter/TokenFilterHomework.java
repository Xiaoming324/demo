package com.itheima.filter;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilterHomework implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String requestURI = httpServletRequest.getRequestURI();

        if (requestURI.contains("/login")) {
            log.info("login请求，放行");
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String token = httpServletRequest.getHeader("token");

        if (token == null || token.isEmpty()) {
            log.info("缺少token，不放行");
            httpServletResponse.setStatus(401);
            return;
        }

        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("toke解析错误，不放行");
            httpServletResponse.setStatus(401);
            return;
        }

        log.info("token解析成功，放行");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
