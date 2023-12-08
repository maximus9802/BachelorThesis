package com.quyvx.main_server.domain.aggregate_models.manager_aggregate;

import com.quyvx.main_server.domain.aggregate_models.EntityAggregateRoot;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
public class Manager extends EntityAggregateRoot {
    private Long memberId;
    private Boolean isDeleted;

    private List<ManagerLocation> managerLocations;
}
