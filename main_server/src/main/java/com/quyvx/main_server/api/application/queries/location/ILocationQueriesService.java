package com.quyvx.main_server.api.application.queries.location;

import com.quyvx.main_server.api.application.models.location.LocationDetails;

import java.util.List;
import java.util.Optional;

public interface ILocationQueriesService {
    Optional<LocationDetails> getLocationDetail(Long locationId);

    List<LocationDetails> listLocationsByCompanyIdAndSearchWithPagination(Long companyId, Long offset, Integer limit, String search);

    Long countLocationsByCompanyIdAndSearchWithPagination(Long companyId, String search);
}
