package com.quyvx.main_server.domain.aggregate_models.status_parking_aggregate;

import com.quyvx.main_server.domain.aggregate_models.EntityAggregateRoot;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class StatusParking extends EntityAggregateRoot {
    private String type;
    private String name;
}
