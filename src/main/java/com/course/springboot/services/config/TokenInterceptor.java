package com.course.springboot.services.config;

import com.course.springboot.services.commons.enums.RestExceptionE;
import com.course.springboot.services.config.error.RestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Value("${custom.headers}")
    private String authHeader;

    private static final Logger LOG = LoggerFactory.getLogger(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws RestException {
        LOG.info("[preHandle][" + request + "]" + "[" + request.getMethod() + "]" + request.getRequestURI());
        String auth = request.getHeader(authHeader);
        if("not-allowed".equals(auth))
            throw new RestException(RestExceptionE.UNAUTHORIZED);
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        LOG.info("[postHandle][" + request + "]");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        LOG.info("[afterCompletion][" + request + "]");
    }
}
