package com.quyvx.main_server.domain.aggregate_models.authentication_history_aggregate;

import com.quyvx.main_server.domain.aggregate_models.EntityAggregateRoot;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class AuthenticationHistory extends EntityAggregateRoot {
    private Long authenLoginId;
    private Long authenLogoutId;
    private Long statusParkingId;
    private Long duration;
    private String licensePlateCode;
}
