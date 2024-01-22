package com.quyvx.main_server.api.dto.log;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthenticationParkingRes {
    private Long handleCode;
}
