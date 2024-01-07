package com.quyvx.main_server.shared.libs.application.dto;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.quyvx.main_server.shared.constants.RoleEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public class UserDetail extends User {

    private final Long id;

    private final List<String> roles;

    private final Long exp;

    public UserDetail(DecodedJWT decodedJWT) {
        super(decodedJWT.getClaim("id").asLong().toString(), "",
                RoleEnum.getAuthorities(List.of(decodedJWT.getClaim("roles").asArray(String.class))));
        this.id = decodedJWT.getClaim("id").asLong();
        this.roles = List.of(decodedJWT.getClaim("roles").asArray(String.class));
        this.exp = decodedJWT.getExpiresAt().getTime();
    }
}
