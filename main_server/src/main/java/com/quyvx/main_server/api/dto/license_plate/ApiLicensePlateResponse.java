package com.quyvx.main_server.api.dto.license_plate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiLicensePlateResponse {
    private String licensePlate;
}
