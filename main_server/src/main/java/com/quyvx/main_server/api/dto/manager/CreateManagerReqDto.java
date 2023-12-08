package com.quyvx.main_server.api.dto.manager;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class CreateManagerReqDto {
    private String loginId;

    private String password;

    private String lastName;

    private String firstName;

    private LocalDate dateOfBirth;

    private String postCode;

    private String address;

    private String email;

    private String phoneNumber;

    private List<Long> locationsManage;
}
