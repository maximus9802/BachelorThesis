package com.quyvx.main_server.domain.aggregate_models.identity_aggregate;

import com.quyvx.main_server.domain.aggregate_models.EntityAggregateRoot;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Identity extends EntityAggregateRoot {
    private String loginId;
    private String password;
    private Boolean isDeleted;
}
