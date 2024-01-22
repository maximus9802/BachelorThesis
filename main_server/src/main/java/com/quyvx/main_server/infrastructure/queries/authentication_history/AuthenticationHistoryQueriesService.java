package com.quyvx.main_server.infrastructure.queries.authentication_history;

import com.quyvx.main_server.api.application.models.authentication_history.AuthenticationHistoryDetail;
import com.quyvx.main_server.api.application.models.authentication_history.AuthenticationHistorySummary;
import com.quyvx.main_server.api.application.queries.authentication_history.IAuthenticationHistoryQueriesService;
import com.quyvx.main_server.infrastructure.jpa_repositories.AuthenticationHistoryJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationHistoryQueriesService implements IAuthenticationHistoryQueriesService {

    private final AuthenticationHistoryJpaRepository authenticationHistoryJpaRepository;

    @Override
    public Long getAuthenticationHistoryInParkingByLicensePlate(String licensePlate) {
        return authenticationHistoryJpaRepository.getAuthenticationHistoryIdInParkingByLicensePlate(licensePlate);
    }

    @Override
    public List<AuthenticationHistorySummary> getAuthenticationHistory(Long offset, Integer limit, Long companyId) {
        return authenticationHistoryJpaRepository.getAuthenticationHistoryWithPagination(offset, limit, companyId);
    }

    @Override
    public Long countAuthenticationHistory(Long companyId) {
        return authenticationHistoryJpaRepository.countAuthenticationHistory(companyId);
    }

    @Override
    public Optional<AuthenticationHistoryDetail> getAuthenticationHistoryDetail(Long id) {
        return authenticationHistoryJpaRepository.getAuthenticationHistoryDetail(id);
    }
}
