package com.quyvx.main_server.domain.aggregate_models.identity_aggregate;

import com.quyvx.main_server.domain.aggregate_models.EntityAggregateRoot;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class IdentityRole extends EntityAggregateRoot {
    private Long identityId;
    private Long roleId;
}
