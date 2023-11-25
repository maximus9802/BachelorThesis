package com.quyvx.main_server.infrastructure.queries.identity;

import com.quyvx.main_server.api.application.models.identity.IdentityLogin;
import com.quyvx.main_server.api.application.queries.identity.IIdentityQueriesService;
import com.quyvx.main_server.infrastructure.jpa_repositories.IdentityJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class IdentityQueriesService implements IIdentityQueriesService {
    private final IdentityJpaRepository identityJpaRepository;

    @Override
    public Optional<IdentityLogin> getIdentityLoginByLoginId(String loginId) {
        return identityJpaRepository.getIdentityByLoginId(loginId);
    }
}
