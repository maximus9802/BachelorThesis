package com.quyvx.main_server.api.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String loginId;
    private String password;
}
