package com.quyvx.main_server.api.application.queries.identity;

import com.quyvx.main_server.api.application.models.identity.IdentityLogin;

import java.util.Optional;

public interface IIdentityQueriesService {
    Optional<IdentityLogin> getIdentityLoginByLoginId(String loginId);
}
