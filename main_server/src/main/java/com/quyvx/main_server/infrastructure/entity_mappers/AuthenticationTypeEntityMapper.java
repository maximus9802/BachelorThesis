package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.authentication_type_aggregate.AuthenticationType;
import com.quyvx.main_server.infrastructure.entities.AuthenticationTypeEntity;
import com.quyvx.main_server.shared.utils.TimeUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AuthenticationTypeEntityMapper {

    public AuthenticationTypeEntity modelToEntity(AuthenticationType model) {
        return AuthenticationTypeEntity.builder()
                .id(model.getId())
                .createAt(TimeUtils.nullOrNow(model.createAt))
                .updateAt(TimeUtils.nullOrNow(model.updateAt))
                .type(model.getType())
                .name(model.getName())
                .build();
    }

    public AuthenticationType entityToModel(AuthenticationTypeEntity entity) {
        if (ObjectUtils.isEmpty(entity)) return null;

        return AuthenticationType.builder()
                .id(entity.getId())
                .createAt(TimeUtils.nullOrNow(entity.getCreateAt()))
                .updateAt(TimeUtils.nullOrNow(entity.getUpdateAt()))
                .type(entity.getType())
                .name(entity.getName())
                .build();
    }
}
