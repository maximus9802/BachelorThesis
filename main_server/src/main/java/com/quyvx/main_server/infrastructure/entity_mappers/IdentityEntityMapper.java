package com.quyvx.main_server.infrastructure.entity_mappers;

import ch.qos.logback.core.util.TimeUtil;
import com.quyvx.main_server.domain.aggregate_models.identity_aggregate.Identity;
import com.quyvx.main_server.domain.aggregate_models.identity_aggregate.IdentityRole;
import com.quyvx.main_server.infrastructure.entities.IdentityEntity;
import com.quyvx.main_server.infrastructure.entities.IdentityRoleEntity;
import com.quyvx.main_server.shared.utils.TimeUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IdentityEntityMapper {
    public IdentityEntity modelToEntity(Identity model) {
        IdentityEntity entity = IdentityEntity.builder()
                .id(model.getId())
                .createAt(model.getCreatedAt())
                .updateAt(model.getUpdateAt())
                .loginId(model.getLoginId())
                .password(model.getPassword())
                .isDeleted(model.getIsDeleted())
                .build();

        List<IdentityRoleEntity> identityRoles = new ArrayList<>();
        if (ObjectUtils.isEmpty(model.getIdentityRoles())) {
            identityRoles = model.getIdentityRoles().stream()
                    .map(identityRole -> (IdentityRoleEntity) IdentityRoleEntity.builder()
                            .id(identityRole.id)
                            .createAt(TimeUtils.nullOrNow(identityRole.createdAt))
                            .updateAt(TimeUtils.nullOrNow(identityRole.updateAt))
                            .identity(entity)
                            .identityId(identityRole.getIdentityId())
                            .roleId(identityRole.getRoleId())
                            .build())
                    .collect(Collectors.toList());
        }

        entity.setIdentityRoles(identityRoles);

        return entity;
    }

    public Identity entityToModel(IdentityEntity entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return null;
        }

        List<IdentityRole> identityRoles = new ArrayList<>();
        if (ObjectUtils.isEmpty(entity.getIdentityRoles())) {
            identityRoles = entity.getIdentityRoles().stream()
                    .map(identityRole -> (IdentityRole) IdentityRole.builder()
                            .createdAt(TimeUtils.nullOrNow(identityRole.getCreateAt()))
                            .updateAt(TimeUtils.nullOrNow(identityRole.getUpdateAt()))
                            .identityId(identityRole.getIdentityId())
                            .roleId(identityRole.getRoleId())
                            .build())
                    .collect(Collectors.toList());
        }

        return Identity.builder()
                .id(entity.getId())
                .createdAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .loginId(entity.getLoginId())
                .password(entity.getPassword())
                .identityRoles(identityRoles)
                .isDeleted(entity.getIsDeleted())
                .build();
    }
}
