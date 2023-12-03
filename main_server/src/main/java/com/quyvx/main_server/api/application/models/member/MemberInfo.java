package com.quyvx.main_server.api.application.models.member;

import java.time.LocalDate;

public interface MemberInfo {
    Long getMemberId();

    Long getIdentityId();

    String getFirstName();

    String getLastName();

    LocalDate getBirthday();

    String getPostCode();

    String getAddress();

    String getEmail();

    String getPhone();
}
