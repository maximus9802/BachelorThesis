package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.location_aggregate.Location;
import com.quyvx.main_server.infrastructure.entities.LocationEntity;
import com.quyvx.main_server.shared.utils.TimeUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class LocationEntityMapper {

    public LocationEntity modelToEntity(Location model) {
        return LocationEntity.builder()
                .id(model.getId())
                .createAt(TimeUtils.nullOrNow(model.createAt))
                .updateAt(TimeUtils.nullOrNow(model.updateAt))
                .companyId(model.getCompanyId())
                .address(model.getAddress())
                .name(model.getName())
                .permanentLink(model.getPermanentLink())
                .isDeleted(model.getIsDeleted())
                .build();
    }

    public Location entityToModel(LocationEntity entity) {
        if (ObjectUtils.isEmpty(entity)) return null;

        return Location.builder()
                .id(entity.getId())
                .createAt(TimeUtils.nullOrNow(entity.getCreateAt()))
                .updateAt(TimeUtils.nullOrNow(entity.getUpdateAt()))
                .companyId(entity.getCompanyId())
                .address(entity.getAddress())
                .name(entity.getName())
                .permanentLink(entity.getPermanentLink())
                .isDeleted(entity.getIsDeleted())
                .build();
    }
}
