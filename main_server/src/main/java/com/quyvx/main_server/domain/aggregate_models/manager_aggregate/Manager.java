package com.quyvx.main_server.domain.aggregate_models.manager_aggregate;

import com.quyvx.main_server.domain.aggregate_models.EntityAggregateRoot;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Manager extends EntityAggregateRoot {
    private Long locationId;
    private Long identityId;
    private Long roleId;
    private Boolean isDeleted;
}
