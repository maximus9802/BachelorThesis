package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.role_aggregate.Role;
import com.quyvx.main_server.infrastructure.entities.RoleEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class RoleEntityMapper {

    public RoleEntity modelToEntity (Role model) {
        return RoleEntity.builder()
                .id(model.id)
                .createAt(model.getCreatedAt())
                .updateAt(model.getUpdateAt())
                .type(model.getType())
                .name(model.getName())
                .build();
    }

    public Role entityToModel (RoleEntity entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return null;
        }

        return Role.builder()
                .id(entity.getId())
                .createdAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .type(entity.getType())
                .name(entity.getName())
                .build();
    }
}
