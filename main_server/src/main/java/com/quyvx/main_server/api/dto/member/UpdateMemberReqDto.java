package com.quyvx.main_server.api.dto.member;

import lombok.Getter;

@Getter
public class UpdateMemberReqDto {
    private Long identityId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String postCode;
    private String address;
    private String email;
    private String phoneNumber;
}
