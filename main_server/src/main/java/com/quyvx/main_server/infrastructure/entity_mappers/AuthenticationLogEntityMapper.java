package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.authentication_log_aggregate.AuthenticationLog;
import com.quyvx.main_server.infrastructure.entities.AuthenticationLogEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

@Service
public class AuthenticationLogEntityMapper {

    public AuthenticationLogEntity modelToEntity(AuthenticationLog model) {
        return AuthenticationLogEntity.builder()
                .id(model.getId())
                .createAt(model.getCreatedAt())
                .updateAt(model.getUpdateAt())
                .cameraId(model.getCameraId())
                .evidenceLink(model.getEvidenceLink())
                .time(model.getTime())
                .date(model.getDate())
                .isDeleted(model.getIsDeleted())
                .build();
    }

    public AuthenticationLog entityToModel(AuthenticationLogEntity entity) {
        if (ObjectUtils.isEmpty(entity)) return null;

        return AuthenticationLog.builder()
                .id(entity.getId())
                .createdAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .cameraId(entity.getCameraId())
                .evidenceLink(entity.getEvidenceLink())
                .time(entity.getTime())
                .date(entity.getDate())
                .isDeleted(entity.getIsDeleted())
                .build();
    }
}
