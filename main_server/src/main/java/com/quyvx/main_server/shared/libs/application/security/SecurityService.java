package com.quyvx.main_server.shared.libs.application.security;

import com.quyvx.main_server.shared.exceptions.UnauthorizationException;
import com.quyvx.main_server.shared.libs.application.dto.UserDetail;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    public UserDetail getUserDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (ObjectUtils.isEmpty(authentication) || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new UnauthorizationException("You are not logged in");
        }
        return (UserDetail) authentication.getPrincipal();
    }

    public Optional<UserDetail> getUserDetailOptional() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (ObjectUtils.isEmpty(authentication) || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        return Optional.of((UserDetail) authentication.getPrincipal());
    }
}
