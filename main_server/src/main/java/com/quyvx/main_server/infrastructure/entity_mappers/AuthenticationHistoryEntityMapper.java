package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.authentication_history_aggregate.AuthenticationHistory;
import com.quyvx.main_server.infrastructure.entities.AuthenticationHistoryEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AuthenticationHistoryEntityMapper {

    public AuthenticationHistoryEntity modelToEntity(AuthenticationHistory model) {
        return AuthenticationHistoryEntity.builder()
                .id(model.getId())
                .createAt(model.getCreatedAt())
                .updateAt(model.getUpdateAt())
                .authenLoginId(model.getAuthenLoginId())
                .authenLogoutId(model.getAuthenLogoutId())
                .statusParkingId(model.getStatusParkingId())
                .duration(model.getDuration())
                .licensePlateCode(model.getLicensePlateCode())
                .build();
    }

    public AuthenticationHistory entityToModel(AuthenticationHistoryEntity entity) {
        if(ObjectUtils.isEmpty(entity)) return null;

        return AuthenticationHistory.builder()
                .id(entity.getId())
                .createdAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .authenLoginId(entity.getAuthenLoginId())
                .authenLogoutId(entity.getAuthenLogoutId())
                .statusParkingId(entity.getStatusParkingId())
                .duration(entity.getDuration())
                .licensePlateCode(entity.getLicensePlateCode())
                .build();
    }
}
