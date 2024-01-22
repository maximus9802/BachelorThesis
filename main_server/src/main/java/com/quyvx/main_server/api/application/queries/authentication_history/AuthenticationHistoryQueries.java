package com.quyvx.main_server.api.application.queries.authentication_history;

import com.quyvx.main_server.api.application.models.authentication_history.AuthenticationHistoryDetail;
import com.quyvx.main_server.api.application.models.authentication_history.AuthenticationHistorySummary;
import com.quyvx.main_server.shared.dto.PaginationMeta;
import com.quyvx.main_server.shared.dto.PaginationResponse;
import com.quyvx.main_server.shared.exceptions.NotFoundException;
import com.quyvx.main_server.shared.utils.PaginationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AuthenticationHistoryQueries {

    private final IAuthenticationHistoryQueriesService authenticationHistoryQueriesService;

    public PaginationResponse<List<AuthenticationHistorySummary>> getAuthenticationHistory(Long offset, Integer limit, Long companyId) {
        offset = PaginationUtils.getOffset(offset);
        limit = PaginationUtils.getLimit(limit);
        List<AuthenticationHistorySummary> authenticationHistorySummaryList;
        Long total;
        authenticationHistorySummaryList = authenticationHistoryQueriesService.getAuthenticationHistory(offset, limit, companyId);
        total = authenticationHistoryQueriesService.countAuthenticationHistory(companyId);
        PaginationMeta meta = PaginationMeta.builder()
                .total(total)
                .offset(offset)
                .limit(limit)
                .build();
        return PaginationResponse.<List<AuthenticationHistorySummary>>builder()
                .data(authenticationHistorySummaryList)
                .meta(meta)
                .build();
    }

    public AuthenticationHistoryDetail getAuthenticationHistoryDetail(Long authenticationHistoryId) {
        return authenticationHistoryQueriesService.getAuthenticationHistoryDetail(authenticationHistoryId).orElseThrow(
                () -> new NotFoundException("not_found_authentication_history")
        );
    }
}
