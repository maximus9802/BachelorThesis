package com.quyvx.main_server.infrastructure.queries.authentication_history;

import com.quyvx.main_server.api.application.queries.authentication_history.IAuthenticationHistoryQueriesService;
import com.quyvx.main_server.infrastructure.jpa_repositories.AuthenticationHistoryJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationHistoryQueriesService implements IAuthenticationHistoryQueriesService {

    private final AuthenticationHistoryJpaRepository authenticationHistoryJpaRepository;

    @Override
    public Long getAuthenticationHistoryInParkingByLicensePlate(String licensePlate) {
        return authenticationHistoryJpaRepository.getAuthenticationHistoryIdInParkingByLicensePlate(licensePlate);
    }
}
