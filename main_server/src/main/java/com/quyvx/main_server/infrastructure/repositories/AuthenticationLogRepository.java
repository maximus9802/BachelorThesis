package com.quyvx.main_server.infrastructure.repositories;

import com.quyvx.main_server.domain.aggregate_models.authentication_log_aggregate.AuthenticationLog;
import com.quyvx.main_server.domain.repositories.IAuthenticationLogRepository;
import com.quyvx.main_server.infrastructure.entities.AuthenticationLogEntity;
import com.quyvx.main_server.infrastructure.entity_mappers.AuthenticationLogEntityMapper;
import com.quyvx.main_server.infrastructure.jpa_repositories.AuthenticationLogJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AuthenticationLogRepository implements IAuthenticationLogRepository {
    private final AuthenticationLogJpaRepository authenticationLogJpaRepository;
    private final AuthenticationLogEntityMapper mapper;
    @Override
    public AuthenticationLog save(AuthenticationLog model) {
        AuthenticationLogEntity entity = mapper.modelToEntity(model);
        entity = authenticationLogJpaRepository.save(entity);
        return mapper.entityToModel(entity);
    }

    @Override
    public void delete(Long id) {
        authenticationLogJpaRepository.deleteById(id);
    }

    @Override
    public Optional<AuthenticationLog> findById(Long id) {
        return authenticationLogJpaRepository.findById(id).map(mapper::entityToModel);
    }
}
