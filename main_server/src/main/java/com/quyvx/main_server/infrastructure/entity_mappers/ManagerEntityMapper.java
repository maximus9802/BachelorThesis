package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.manager_aggregate.Manager;
import com.quyvx.main_server.infrastructure.entities.ManagerEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class ManagerEntityMapper {
    public ManagerEntity modelToEntity(Manager model) {
        return ManagerEntity.builder()
                .id(model.getId())
                .createAt(model.getCreatedAt())
                .updateAt(model.getUpdateAt())
                .identityId(model.getIdentityId())
                .locationId(model.getLocationId())
                .roleId(model.getRoleId())
                .isDeleted(model.getIsDeleted())
                .build();
    }

    public Manager entityToModel(ManagerEntity entity) {
        if (ObjectUtils.isEmpty(entity)) return null;

        return Manager.builder()
                .id(entity.getId())
                .createdAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .identityId(entity.getIdentityId())
                .locationId(entity.getLocationId())
                .roleId(entity.getRoleId())
                .isDeleted(entity.getIsDeleted())
                .build();
    }
}
