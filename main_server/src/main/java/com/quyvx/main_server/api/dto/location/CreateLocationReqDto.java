package com.quyvx.main_server.api.dto.location;

import lombok.Getter;

@Getter
public class CreateLocationReqDto {
    private Long companyId;

    private String locationName;

    private String address;

    private String permanentLink;
}
