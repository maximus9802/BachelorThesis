package com.quyvx.main_server.api.application.queries.location;

import com.quyvx.main_server.api.application.models.location.LocationDetails;
import com.quyvx.main_server.shared.dto.PaginationMeta;
import com.quyvx.main_server.shared.dto.PaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LocationQueries {
    private final ILocationQueriesService locationQueriesService;

    public PaginationResponse<List<LocationDetails>> listLocationsByCompanyIdAndSearchWithPagination(Long companyId, Long offset,
                                                                                                     Integer limit, String search) {
        List<LocationDetails> locations = locationQueriesService
                .listLocationsByCompanyIdAndSearchWithPagination(companyId, offset, limit, search);

        Long total = locationQueriesService.countLocationsByCompanyIdAndSearchWithPagination(companyId, search);

        PaginationMeta meta = PaginationMeta.builder()
                .offset(offset)
                .limit(limit)
                .total(total)
                .build();

        return PaginationResponse.<List<LocationDetails>>builder()
                .data(locations)
                .meta(meta)
                .build();
    }
}
