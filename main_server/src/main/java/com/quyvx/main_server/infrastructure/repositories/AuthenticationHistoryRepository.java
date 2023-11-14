package com.quyvx.main_server.infrastructure.repositories;

import com.quyvx.main_server.domain.aggregate_models.authentication_history_aggregate.AuthenticationHistory;
import com.quyvx.main_server.domain.repositories.IAuthenticationHistoryRepository;
import com.quyvx.main_server.infrastructure.entities.AuthenticationHistoryEntity;
import com.quyvx.main_server.infrastructure.entity_mappers.AuthenticationHistoryEntityMapper;
import com.quyvx.main_server.infrastructure.jpa_repositories.AuthenticationHistoryJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AuthenticationHistoryRepository implements IAuthenticationHistoryRepository {
    private final AuthenticationHistoryJpaRepository authenticationHistoryJpaRepository;
    private final AuthenticationHistoryEntityMapper mapper;
    @Override
    public AuthenticationHistory save(AuthenticationHistory model) {
        AuthenticationHistoryEntity entity = mapper.modelToEntity(model);
        entity = authenticationHistoryJpaRepository.save(entity);
        return mapper.entityToModel(entity);
    }

    @Override
    public void delete(Long id) {
        authenticationHistoryJpaRepository.deleteById(id);
    }

    @Override
    public Optional<AuthenticationHistory> findById(Long id) {
        return authenticationHistoryJpaRepository.findById(id).map(mapper::entityToModel);
    }
}
