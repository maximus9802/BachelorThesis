package com.quyvx.main_server.api.application.queries.identity;

import com.quyvx.main_server.api.application.models.identity.IdentityLogin;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class IdentityQueries {
    private final IIdentityQueriesService identityQueriesService;

    public Optional<IdentityLogin> getIdentityByLoginId(String loginId) {
        return identityQueriesService.getIdentityLoginByLoginId(loginId);
    }
}
