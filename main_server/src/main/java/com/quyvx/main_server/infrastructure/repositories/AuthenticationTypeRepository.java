package com.quyvx.main_server.infrastructure.repositories;

import com.quyvx.main_server.domain.aggregate_models.authentication_type_aggregate.AuthenticationType;
import com.quyvx.main_server.domain.repositories.IAuthenticationTypeRepository;
import com.quyvx.main_server.infrastructure.entities.AuthenticationTypeEntity;
import com.quyvx.main_server.infrastructure.entity_mappers.AuthenticationTypeEntityMapper;
import com.quyvx.main_server.infrastructure.jpa_repositories.AuthenticationTypeJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AuthenticationTypeRepository implements IAuthenticationTypeRepository {
    private final AuthenticationTypeJpaRepository authenticationTypeJpaRepository;
    private final AuthenticationTypeEntityMapper mapper;
    @Override
    public AuthenticationType save(AuthenticationType model) {
        AuthenticationTypeEntity entity = mapper.modelToEntity(model);
        entity = authenticationTypeJpaRepository.save(entity);
        return mapper.entityToModel(entity);
    }

    @Override
    public void delete(Long id) {
        authenticationTypeJpaRepository.deleteById(id);
    }

    @Override
    public Optional<AuthenticationType> findById(Long id) {
        return authenticationTypeJpaRepository.findById(id).map(mapper::entityToModel);
    }
}
