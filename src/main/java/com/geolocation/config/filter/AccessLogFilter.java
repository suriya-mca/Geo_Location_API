package com.geolocation.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom filter for logging access information in a Spring Boot application.
 * This filter logs details such as client IP, HTTP method, URI, response content type, status, and processing time.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AccessLogFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(AccessLogFilter.class);

    @Override
    protected void doFilterInternal( HttpServletRequest request, 
                                     HttpServletResponse response, 
                                     FilterChain filterChain )
                                     throws ServletException, IOException {

        if (!request.getRequestURI().toLowerCase().contains("/api")) {
            filterChain.doFilter(request, response);          
        } 
        
        long startTime = System.currentTimeMillis();

        try {
            filterChain.doFilter(request, response);
        } 
        finally {
            long duration = System.currentTimeMillis() - startTime;
            logRequestInfo(request, response, duration);
}

    }

    private void logRequestInfo( HttpServletRequest request, 
                                 HttpServletResponse response, 
                                 long duration ) {
    
        String remoteIpAddress = request.getHeader("X-FORWARDED-FOR");

        if (remoteIpAddress == null || remoteIpAddress.isEmpty()) {
            remoteIpAddress = request.getRemoteAddr();
        }

        log.info("{} {} {} {} {} {}ms",
                remoteIpAddress,
                request.getMethod(),
                request.getRequestURI(),
                response.getContentType(),
                response.getStatus(),
                duration);
    }

}
