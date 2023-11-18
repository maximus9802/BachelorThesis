package com.quyvx.main_server.api.dto.auth;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String loginId;
    private String password;
}
