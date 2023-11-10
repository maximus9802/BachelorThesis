package com.quyvx.main_server.infrastructure.repositories;

import com.quyvx.main_server.domain.aggregate_models.role_aggregate.Role;
import com.quyvx.main_server.domain.repositories.IRoleRepository;
import com.quyvx.main_server.infrastructure.entities.RoleEntity;
import com.quyvx.main_server.infrastructure.entity_mappers.RoleEntityMapper;
import com.quyvx.main_server.infrastructure.jpa_repositories.RoleJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class RoleRepository implements IRoleRepository{
    private final RoleJpaRepository roleJpaRepository;
    private final RoleEntityMapper mapper;

    @Override
    public Role save(Role model) {
        RoleEntity roleEntity = mapper.modelToEntity(model);
        roleEntity = roleJpaRepository.save(roleEntity);
        return mapper.entityToModel(roleEntity);
    }

    @Override
    public void delete(Long id) {
        roleJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleJpaRepository.findById(id).map(mapper::entityToModel);
    }
}
