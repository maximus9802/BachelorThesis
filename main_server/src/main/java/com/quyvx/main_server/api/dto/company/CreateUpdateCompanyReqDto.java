package com.quyvx.main_server.api.dto.company;

import lombok.Getter;

@Getter
public class CreateUpdateCompanyReqDto {

    private String loginId;

    private String password;

    private String companyName;

    private String address;

    private String phoneNumber;

    private String email;

    private String permanentLink;
}
