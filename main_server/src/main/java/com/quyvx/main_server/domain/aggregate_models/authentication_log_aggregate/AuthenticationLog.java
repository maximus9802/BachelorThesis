package com.quyvx.main_server.domain.aggregate_models.authentication_log_aggregate;

import com.quyvx.main_server.domain.aggregate_models.EntityAggregateRoot;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@SuperBuilder
@Getter
public class AuthenticationLog extends EntityAggregateRoot {
    private Long cameraId;
    private String evidenceLink;
    private LocalTime time;
    private LocalDate date;
    private Boolean isDeleted;
}
