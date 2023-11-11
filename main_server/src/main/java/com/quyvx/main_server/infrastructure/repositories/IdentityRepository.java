package com.quyvx.main_server.infrastructure.repositories;

import com.quyvx.main_server.domain.aggregate_models.identity_aggregate.Identity;
import com.quyvx.main_server.domain.repositories.IIdentityRepository;
import com.quyvx.main_server.infrastructure.entities.IdentityEntity;
import com.quyvx.main_server.infrastructure.entity_mappers.IdentityEntityMapper;
import com.quyvx.main_server.infrastructure.jpa_repositories.IdentityJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class IdentityRepository implements IIdentityRepository {
    private final IdentityJpaRepository identityJpaRepository;
    private final IdentityEntityMapper mapper;
    @Override
    public Identity save(Identity model) {
        IdentityEntity entity = mapper.modelToEntity(model);
        entity = identityJpaRepository.save(entity);
        return mapper.entityToModel(entity);
    }

    @Override
    public void delete(Long id) {
        identityJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Identity> findById(Long id) {
        return identityJpaRepository.findById(id).map(mapper::entityToModel);
    }
}
