package com.example.exspringboot.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;

@Component
public class RequestLoggingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        chain.doFilter(requestWrapper, response);

        logRequest(requestWrapper);
    }

    private void logRequest(ContentCachingRequestWrapper request) {
        String queryString = request.getQueryString();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        log.info("Request: {} {}{}", method, requestURI, (queryString == null ? "" : "?" + queryString));

        String requestBody = new String(request.getContentAsByteArray());
        if (!requestBody.isEmpty()) {
            log.info("Request Body: {}", requestBody);
        }
    }
}
