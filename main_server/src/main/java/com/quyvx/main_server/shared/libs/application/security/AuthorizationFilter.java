package com.quyvx.main_server.shared.libs.application.security;

import com.quyvx.main_server.shared.exceptions.UnauthorizationException;
import com.quyvx.main_server.shared.libs.application.dto.UserDetail;
import com.quyvx.main_server.shared.libs.infrastructure.services.SharedTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private final SharedTokenService sharedTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization == null || !authorization.startsWith(StringUtils.joinWith(" ", "Bearer"))) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String token = authorization.substring(7);
            UserDetail userDetail = sharedTokenService.verify(token);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new UnauthorizationException(e.getMessage());
        }
    }
}
