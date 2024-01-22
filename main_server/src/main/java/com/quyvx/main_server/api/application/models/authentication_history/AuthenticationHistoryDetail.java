package com.quyvx.main_server.api.application.models.authentication_history;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AuthenticationHistoryDetail {
    String getLocation();

    LocalDate getDateLogIn();

    LocalTime getTimeLogIn();

    String getEvidenceLogIn();

    LocalDate getDateLogOut();

    LocalTime getTimeLogOut();

    String getEvidenceLogOut();

    Long getDuration();

    String getStatusParking();

    String getLicensePlate();
}
