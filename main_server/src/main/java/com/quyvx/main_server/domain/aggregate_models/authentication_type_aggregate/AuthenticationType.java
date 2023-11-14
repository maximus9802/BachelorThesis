package com.quyvx.main_server.domain.aggregate_models.authentication_type_aggregate;

import com.quyvx.main_server.domain.aggregate_models.EntityAggregateRoot;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class AuthenticationType extends EntityAggregateRoot {
    private String type;
    private String name;
}
