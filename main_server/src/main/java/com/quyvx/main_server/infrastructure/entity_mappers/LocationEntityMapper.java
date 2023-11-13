package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.location_aggregate.Location;
import com.quyvx.main_server.infrastructure.entities.LocationEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class LocationEntityMapper {

    public LocationEntity modelToEntity(Location model) {
        return LocationEntity.builder()
                .id(model.getId())
                .createAt(model.getCreatedAt())
                .updateAt(model.getUpdateAt())
                .companyId(model.getCompanyId())
                .address(model.getAddress())
                .name(model.getName())
                .isDeleted(model.getIsDeleted())
                .build();
    }

    public Location entityToModel(LocationEntity entity) {
        if (ObjectUtils.isEmpty(entity)) return null;

        return Location.builder()
                .id(entity.getId())
                .createdAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .companyId(entity.getCompanyId())
                .address(entity.getAddress())
                .name(entity.getName())
                .isDeleted(entity.getIsDeleted())
                .build();
    }
}
