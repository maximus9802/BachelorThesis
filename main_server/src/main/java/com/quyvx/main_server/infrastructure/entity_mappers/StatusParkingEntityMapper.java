package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.status_parking_aggregate.StatusParking;
import com.quyvx.main_server.infrastructure.entities.StatusParkingEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class StatusParkingEntityMapper {

    public StatusParkingEntity modelToEntity(StatusParking model) {
        return StatusParkingEntity.builder()
                .id(model.getId())
                .createAt(model.getCreatedAt())
                .updateAt(model.getUpdateAt())
                .type(model.getType())
                .name(model.getName())
                .build();
    }

    public StatusParking entityToModel(StatusParkingEntity entity) {
        if (ObjectUtils.isEmpty(entity)) return null;

        return StatusParking.builder()
                .id(entity.getId())
                .createdAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .type(entity.getType())
                .name(entity.getName())
                .build();
    }
}
