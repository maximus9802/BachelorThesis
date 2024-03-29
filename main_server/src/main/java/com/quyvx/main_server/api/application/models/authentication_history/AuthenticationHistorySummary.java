package com.quyvx.main_server.api.application.models.authentication_history;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AuthenticationHistorySummary {

    Long getId();
    String getLocation();

    LocalDate getDateLogIn();

    LocalTime getTimeLogIn();

    LocalDate getDateLogOut();

    LocalTime getTimeLogOut();

    String getStatusParking();

    String getLicensePlate();
}
