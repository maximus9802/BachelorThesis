package com.quyvx.main_server.infrastructure.queries.status_parking;

import com.quyvx.main_server.api.application.queries.status_parking.IStatusParkingQueriesService;
import com.quyvx.main_server.infrastructure.jpa_repositories.StatusParkingJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StatusParkingQueriesService implements IStatusParkingQueriesService {

    private final StatusParkingJpaRepository statusParkingJpaRepository;

    @Override
    public Optional<Long> findStatusParkingIdByType(String statusParkingType) {
        return statusParkingJpaRepository.findStatusParkingIdByStatusParkingType(statusParkingType);
    }
}
