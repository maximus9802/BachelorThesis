package com.quyvx.main_server.api.application.queries.authentication_history;

import com.quyvx.main_server.api.application.models.authentication_history.AuthenticationHistoryDetail;
import com.quyvx.main_server.api.application.models.authentication_history.AuthenticationHistorySummary;

import java.util.List;
import java.util.Optional;

public interface IAuthenticationHistoryQueriesService {
    Long getAuthenticationHistoryInParkingByLicensePlate(String licensePlate);

    List<AuthenticationHistorySummary> getAuthenticationHistory(Long offset, Integer limit, Long companyId);

    Long countAuthenticationHistory(Long companyId);

    Optional<AuthenticationHistoryDetail> getAuthenticationHistoryDetail(Long id);
}
