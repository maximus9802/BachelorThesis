package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.authentication_type_aggregate.AuthenticationType;
import com.quyvx.main_server.infrastructure.entities.AuthenticationTypeEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AuthenticationTypeEntityMapper {

    public AuthenticationTypeEntity modelToEntity(AuthenticationType model) {
        return AuthenticationTypeEntity.builder()
                .id(model.getId())
                .createAt(model.getCreatedAt())
                .updateAt(model.getUpdateAt())
                .type(model.getType())
                .name(model.getName())
                .build();
    }

    public AuthenticationType entityToModel(AuthenticationTypeEntity entity) {
        if (ObjectUtils.isEmpty(entity)) return null;

        return AuthenticationType.builder()
                .id(entity.getId())
                .createdAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .type(entity.getType())
                .name(entity.getName())
                .build();
    }
}
