package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.manager_aggregate.Manager;
import com.quyvx.main_server.domain.aggregate_models.manager_aggregate.ManagerLocation;
import com.quyvx.main_server.infrastructure.entities.ManagerEntity;
import com.quyvx.main_server.infrastructure.entities.ManagerLocationEntity;
import com.quyvx.main_server.shared.utils.TimeUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerEntityMapper {
    public ManagerEntity modelToEntity(Manager model) {
        ManagerEntity entity = ManagerEntity.builder()
                .id(model.getId())
                .createAt(TimeUtils.nullOrNow(model.createAt))
                .updateAt(TimeUtils.nullOrNow(model.updateAt))
                .memberId(model.getMemberId())
                .isDeleted(model.getIsDeleted())
                .build();

        List<ManagerLocationEntity> managerLocations = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(model.getManagerLocations())) {
            managerLocations = model.getManagerLocations().stream()
                    .map(managerLocation -> (ManagerLocationEntity) ManagerLocationEntity.builder()
                            .id(managerLocation.id)
                            .createAt(TimeUtils.nullOrNow(managerLocation.createAt))
                            .updateAt(TimeUtils.nullOrNow(managerLocation.updateAt))
                            .manager(entity)
                            .managerId(managerLocation.getManagerId())
                            .locationId(managerLocation.getLocationId())
                            .build())
                    .collect(Collectors.toList());
        }
        entity.setManagerLocations(managerLocations);

        return entity;
    }

    public Manager entityToModel(ManagerEntity entity) {
        if (ObjectUtils.isEmpty(entity)) return null;

        List<ManagerLocation> managerLocations = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(entity.getManagerLocations())) {
            managerLocations = entity.getManagerLocations().stream()
                    .map(managerLocation -> (ManagerLocation) ManagerLocation.builder()
                            .id(managerLocation.getId())
                            .createAt(TimeUtils.nullOrNow(managerLocation.getCreateAt()))
                            .updateAt(TimeUtils.nullOrNow(managerLocation.getUpdateAt()))
                            .managerId(managerLocation.getManagerId())
                            .locationId(managerLocation.getLocationId())
                            .build())
                    .collect(Collectors.toList());
        }

        return Manager.builder()
                .id(entity.getId())
                .createAt(TimeUtils.nullOrNow(entity.getCreateAt()))
                .updateAt(TimeUtils.nullOrNow(entity.getUpdateAt()))
                .memberId(entity.getMemberId())
                .managerLocations(managerLocations)
                .isDeleted(entity.getIsDeleted())
                .build();
    }
}
