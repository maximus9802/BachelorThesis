package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.identity_aggregate.Identity;
import com.quyvx.main_server.infrastructure.entities.IdentityEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class IdentityEntityMapper {
    public IdentityEntity modelToEntity(Identity model) {
        return IdentityEntity.builder()
                .id(model.getId())
                .createAt(model.getCreatedAt())
                .updateAt(model.getUpdateAt())
                .loginId(model.getLoginId())
                .password(model.getPassword())
                .isDeleted(model.getIsDeleted())
                .build();
    }

    public Identity entityToModel(IdentityEntity entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return null;
        }

        return Identity.builder()
                .id(entity.getId())
                .createdAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .loginId(entity.getLoginId())
                .password(entity.getPassword())
                .isDeleted(entity.getIsDeleted())
                .build();
    }
}
