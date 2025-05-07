
package com.example.tmstraining.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class VerifyConnectFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws
            ServletException, IOException {

        String tmsConnectKey = request.getHeader("TMS-CONNECT-KEY");

        if (tmsConnectKey == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("Unauthorized with empty api key");
            return;
        }

        if (!tmsConnectKey.equals("tms-connect-key")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("Unauthorized with invalid api key");
            return;
        }

        doFilter(request, response, filterChain);

    }
}
