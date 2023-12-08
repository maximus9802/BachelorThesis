package com.quyvx.main_server.api.application.services.manager;

import com.quyvx.main_server.domain.aggregate_models.location_aggregate.Location;
import com.quyvx.main_server.domain.aggregate_models.manager_aggregate.Manager;
import com.quyvx.main_server.domain.aggregate_models.manager_aggregate.ManagerLocation;
import com.quyvx.main_server.infrastructure.repositories.LocationRepository;
import com.quyvx.main_server.infrastructure.repositories.ManagerRepository;
import com.quyvx.main_server.shared.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final LocationRepository locationRepository;

    public Long createManager(Long memberId, List<Long> locationsManage) {
        List<ManagerLocation> managerLocations = new ArrayList<>();
         for (Long locationId : locationsManage) {
             Optional<Location> location = locationRepository.findById(locationId);
             if (location.isEmpty()) {
                 throw new NotFoundException("location_not_found");
             }
             ManagerLocation managerLocation = ManagerLocation.builder()
                     .locationId(locationId)
                     .build();
            managerLocations.add(managerLocation);
         }

        Manager manager = Manager.builder()
                .memberId(memberId)
                .managerLocations(managerLocations)
                .isDeleted(false)
                .build();
        Manager savedManager = managerRepository.save(manager);

        log.info("----- Manager id {} with member id {} is created", savedManager.id, memberId);
        return savedManager.id;
    }
}
