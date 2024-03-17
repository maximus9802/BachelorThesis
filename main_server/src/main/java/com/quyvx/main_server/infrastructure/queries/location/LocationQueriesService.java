package com.quyvx.main_server.infrastructure.queries.location;

import com.quyvx.main_server.api.application.models.location.LocationDetails;
import com.quyvx.main_server.api.application.queries.location.ILocationQueriesService;
import com.quyvx.main_server.infrastructure.jpa_repositories.LocationJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationQueriesService implements ILocationQueriesService {
    private final LocationJpaRepository locationJpaRepository;

    @Override
    public Optional<LocationDetails> getLocationDetail(Long locationId) {
        return locationJpaRepository.getLocationDetail(locationId);
    }

    @Override
    public List<LocationDetails> listLocationsByCompanyIdAndSearchWithPagination(Long companyId, Long offset, Integer limit, String search) {
        return locationJpaRepository.listLocationsByCompanyIdAndSearchWithPagination(companyId, offset, limit, search);
    }

    @Override
    public Long countLocationsByCompanyIdAndSearchWithPagination(Long companyId, String search) {
        return locationJpaRepository.countLocationsByCompanyIdAndSearchWithPagination(companyId, search);
    }
}
