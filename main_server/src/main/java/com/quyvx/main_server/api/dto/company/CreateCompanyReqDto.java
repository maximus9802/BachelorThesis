package com.quyvx.main_server.api.dto.company;

import lombok.*;

@Getter
public class CreateCompanyReqDto {

    private String loginId;

    private String password;

    private String companyName;

    private String address;

    private String phoneNumber;

    private String permanentLink;
}
