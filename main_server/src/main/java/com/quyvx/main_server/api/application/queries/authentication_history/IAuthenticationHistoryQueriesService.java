package com.quyvx.main_server.api.application.queries.authentication_history;

public interface IAuthenticationHistoryQueriesService {
    Long getAuthenticationHistoryInParkingByLicensePlate(String licensePlate);
}
