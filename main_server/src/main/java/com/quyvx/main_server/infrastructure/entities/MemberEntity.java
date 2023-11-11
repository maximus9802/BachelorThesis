package com.quyvx.main_server.infrastructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "members")
public class MemberEntity extends BaseEntity{
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
