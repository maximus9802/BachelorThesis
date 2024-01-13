package com.quyvx.main_server.api.dto.camera;

import lombok.Getter;

@Getter
public class CreateCameraReqDto {
    private Long companyId;
    private Long locationId;
    private String name;
    private String typeAuth;
}
