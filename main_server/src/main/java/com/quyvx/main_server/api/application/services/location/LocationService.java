package com.quyvx.main_server.api.application.services.location;

import com.quyvx.main_server.domain.aggregate_models.location_aggregate.Location;
import com.quyvx.main_server.infrastructure.repositories.LocationRepository;
import com.quyvx.main_server.shared.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public boolean isLocationOfCompany(Long companyId, Long locationId) {
        Optional<Location> location = locationRepository.findById(locationId);
        if (location.isEmpty()) {
            throw new NotFoundException("not_found_location");
        }
        return Objects.equals(location.get().getCompanyId(), companyId);
    }
}
