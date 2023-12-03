package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.authentication_history_aggregate.AuthenticationHistory;
import com.quyvx.main_server.infrastructure.entities.AuthenticationHistoryEntity;
import com.quyvx.main_server.shared.utils.TimeUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AuthenticationHistoryEntityMapper {

    public AuthenticationHistoryEntity modelToEntity(AuthenticationHistory model) {
        return AuthenticationHistoryEntity.builder()
                .id(model.getId())
                .createAt(TimeUtils.nullOrNow(model.createAt))
                .updateAt(TimeUtils.nullOrNow(model.updateAt))
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
                .createAt(TimeUtils.nullOrNow(entity.getCreateAt()))
                .updateAt(TimeUtils.nullOrNow(entity.getUpdateAt()))
                .authenLoginId(entity.getAuthenLoginId())
                .authenLogoutId(entity.getAuthenLogoutId())
                .statusParkingId(entity.getStatusParkingId())
                .duration(entity.getDuration())
                .licensePlateCode(entity.getLicensePlateCode())
                .build();
    }
}
