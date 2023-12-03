package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.camera_aggregate.Camera;
import com.quyvx.main_server.infrastructure.entities.CameraEntity;
import com.quyvx.main_server.shared.utils.TimeUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class CameraEntityMapper {
    public CameraEntity modelToEntity(Camera model) {
        return CameraEntity.builder()
                .id(model.getId())
                .createAt(TimeUtils.nullOrNow(model.createAt))
                .updateAt(TimeUtils.nullOrNow(model.updateAt))
                .locationId(model.getLocationId())
                .name(model.getName())
                .typeAuthenId(model.getTypeAuthenId())
                .isDeleted(model.getIsDeleted())
                .build();
    }

    public Camera entityToModel(CameraEntity entity) {
        if (ObjectUtils.isEmpty(entity)) return null;

        return Camera.builder()
                .id(entity.getId())
                .createAt(TimeUtils.nullOrNow(entity.getCreateAt()))
                .updateAt(TimeUtils.nullOrNow(entity.getUpdateAt()))
                .locationId(entity.getLocationId())
                .typeAuthenId(entity.getTypeAuthenId())
                .name(entity.getName())
                .isDeleted(entity.getIsDeleted())
                .build();
    }
}
