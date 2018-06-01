package com.roger.boot.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalField;

public class AccessRecorderFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(AccessRecorderFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        if (uri.endsWith(".js")
                || uri.endsWith(".css")
                || uri.endsWith(".map")
                || uri.endsWith(".jpg")
                || uri.endsWith(".png")
                ) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String userAgent = request.getHeader("user-agent");
        String ip = request.getRemoteAddr();
        Long startTime = LocalDate.now().toEpochDay();
        //将请求向后推送，到下一个filter或者最终到Controller执行
        filterChain.doFilter(servletRequest, servletResponse);
        Long endTime = LocalDate.now().toEpochDay();
        logger.info("uri:{},ip:{},accessTime:{},userAgent:{}", uri, ip, (endTime - startTime), userAgent);
    }

    @Override
    public void destroy() {

    }
}
