package com.quyvx.main_server.api.dto.log;

import lombok.Getter;

@Getter
public class LogInParkingReq {
    private String uuidCamera;
    private String imageUrl;
}
