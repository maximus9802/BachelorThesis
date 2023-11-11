package com.quyvx.main_server.domain.aggregate_models.member_aggregate;

import com.quyvx.main_server.domain.aggregate_models.EntityAggregateRoot;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Getter
public class Member extends EntityAggregateRoot {
    private Long identityId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String postCode;
    private String address;
    private String email;
    private String phoneNumber;
    private Boolean isDeleted;
}
