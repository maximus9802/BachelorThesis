package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.role_aggregate.Role;
import com.quyvx.main_server.infrastructure.entities.RoleEntity;
import com.quyvx.main_server.shared.utils.TimeUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class RoleEntityMapper {

    public RoleEntity modelToEntity (Role model) {
        return RoleEntity.builder()
                .id(model.id)
                .createAt(TimeUtils.nullOrNow(model.createAt))
                .updateAt(TimeUtils.nullOrNow(model.updateAt))
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
                .createAt(TimeUtils.nullOrNow(entity.getCreateAt()))
                .updateAt(TimeUtils.nullOrNow(entity.getUpdateAt()))
                .type(entity.getType())
                .name(entity.getName())
                .build();
    }
}
