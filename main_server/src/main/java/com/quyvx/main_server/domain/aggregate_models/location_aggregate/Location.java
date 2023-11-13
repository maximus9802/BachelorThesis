package com.quyvx.main_server.domain.aggregate_models.location_aggregate;

import com.quyvx.main_server.domain.aggregate_models.EntityAggregateRoot;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Location extends EntityAggregateRoot {
    private Long companyId;
    private String name;
    private String address;
    private Boolean isDeleted;
}
