package com.quyvx.main_server.infrastructure.queries.authentication_type;

import com.quyvx.main_server.api.application.queries.authentication_type.IAuthenticationTypeQueriesService;
import com.quyvx.main_server.infrastructure.jpa_repositories.AuthenticationTypeJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationTypeQueriesService implements IAuthenticationTypeQueriesService {

    private final AuthenticationTypeJpaRepository authenticationTypeJpaRepository;
    @Override
    public Long getAuthenticationTypeIdByTypeName(String typeName) {
        return authenticationTypeJpaRepository.getAuthenticationTypeIdByTypeName(typeName);
    }

    @Override
    public String getAuthenticationTypeById(Long typeId) {
        return authenticationTypeJpaRepository.getAuthenticationTypeById(typeId);
    }
}
