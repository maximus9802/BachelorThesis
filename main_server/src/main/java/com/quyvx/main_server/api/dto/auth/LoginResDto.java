package com.quyvx.main_server.api.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginResDto {
    private String accessToken;
    private String refreshToken;
}
