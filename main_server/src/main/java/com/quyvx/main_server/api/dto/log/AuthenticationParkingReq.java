package com.quyvx.main_server.api.dto.log;

import lombok.Getter;

@Getter
public class AuthenticationParkingReq {
    private String cameraUUID;
    private String imageUrl;
    private String licensePlate;
}
