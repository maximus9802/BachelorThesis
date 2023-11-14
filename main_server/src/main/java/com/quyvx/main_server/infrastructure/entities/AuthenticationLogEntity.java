package com.quyvx.main_server.infrastructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "authentication_logs")
public class AuthenticationLogEntity extends BaseEntity{
    private Long cameraId;
    private String evidenceLink;
    private LocalTime time;
    private LocalDate date;
    private Boolean isDeleted;
}
