package com.quyvx.main_server.domain.aggregate_models.camera_aggregate;

import com.quyvx.main_server.domain.aggregate_models.EntityAggregateRoot;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Camera extends EntityAggregateRoot {
    private Long locationId;
    private String name;
    private Long typeAuthenId;
    private String cameraUUID;
    private Boolean isDeleted;
}
