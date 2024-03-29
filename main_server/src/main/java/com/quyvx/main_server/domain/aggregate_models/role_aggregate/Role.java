package com.quyvx.main_server.domain.aggregate_models.role_aggregate;

import com.quyvx.main_server.domain.aggregate_models.EntityAggregateRoot;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Role extends EntityAggregateRoot {
    private String type;
    private String name;
}
