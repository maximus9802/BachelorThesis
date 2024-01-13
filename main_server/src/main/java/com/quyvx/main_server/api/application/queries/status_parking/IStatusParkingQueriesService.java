package com.quyvx.main_server.api.application.queries.status_parking;

import java.util.Optional;

public interface IStatusParkingQueriesService {

    Optional<Long> findStatusParkingIdByType(String statusParkingType);
}
