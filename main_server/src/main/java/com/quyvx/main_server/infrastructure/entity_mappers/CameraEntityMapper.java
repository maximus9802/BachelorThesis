package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.camera_aggregate.Camera;
import com.quyvx.main_server.infrastructure.entities.CameraEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class CameraEntityMapper {
    public CameraEntity modelToEntity(Camera model) {
        return CameraEntity.builder()
                .id(model.getId())
                .createAt(model.getCreatedAt())
                .updateAt(model.getUpdateAt())
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
                .createdAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .locationId(entity.getLocationId())
                .typeAuthenId(entity.getTypeAuthenId())
                .name(entity.getName())
                .isDeleted(entity.getIsDeleted())
                .build();
    }
}
