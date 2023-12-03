package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.status_parking_aggregate.StatusParking;
import com.quyvx.main_server.infrastructure.entities.StatusParkingEntity;
import com.quyvx.main_server.shared.utils.TimeUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class StatusParkingEntityMapper {

    public StatusParkingEntity modelToEntity(StatusParking model) {
        return StatusParkingEntity.builder()
                .id(model.getId())
                .createAt(TimeUtils.nullOrNow(model.createAt))
                .updateAt(TimeUtils.nullOrNow(model.updateAt))
                .type(model.getType())
                .name(model.getName())
                .build();
    }

    public StatusParking entityToModel(StatusParkingEntity entity) {
        if (ObjectUtils.isEmpty(entity)) return null;

        return StatusParking.builder()
                .id(entity.getId())
                .createAt(TimeUtils.nullOrNow(entity.getCreateAt()))
                .updateAt(TimeUtils.nullOrNow(entity.getUpdateAt()))
                .type(entity.getType())
                .name(entity.getName())
                .build();
    }
}
