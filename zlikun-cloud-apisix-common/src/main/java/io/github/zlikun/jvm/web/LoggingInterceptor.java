package io.github.zlikun.jvm.web;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

import static io.github.zlikun.jvm.constant.HeaderConstants.TRACE_HEADER_NAME;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String xRequestId = request.getHeader(TRACE_HEADER_NAME);
        MDC.put(TRACE_HEADER_NAME, xRequestId);
        log.info("Access Begin: {} {}, Query: {}", request.getMethod(), request.getRequestURI(), request.getQueryString());
        this.printRequestHeaders(request);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        try {
            if (ex != null) {
                log.error("Access Error: {} {}", request.getMethod(), request.getRequestURI(), ex);
            } else {
                log.error("Access End: {} {}", request.getMethod(), request.getRequestURI());
            }
        } finally {
            MDC.remove(TRACE_HEADER_NAME);
        }
    }

    private void printRequestHeaders(HttpServletRequest request) {
        final Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            final String name = headers.nextElement();
            log.info("Header -> {}: {}", name, request.getHeader(name));
        }
    }

}
