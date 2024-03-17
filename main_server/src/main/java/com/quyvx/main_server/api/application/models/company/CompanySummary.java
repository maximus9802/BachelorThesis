package com.quyvx.main_server.api.application.models.company;

public interface CompanySummary {
    Long getId();

    Long getIdentityId();

    String getCompanyName();

    String getAddress();

    String getPhoneNumber();

    String getEmail();

    String getPermanentLink();
}
