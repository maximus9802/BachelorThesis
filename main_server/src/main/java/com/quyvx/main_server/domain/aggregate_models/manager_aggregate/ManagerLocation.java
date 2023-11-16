package com.quyvx.main_server.domain.aggregate_models.manager_aggregate;

import com.quyvx.main_server.domain.aggregate_models.EntityAggregateRoot;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ManagerLocation extends EntityAggregateRoot {
    private Long managerId;
    private Long locationId;
}
