package com.quyvx.main_server.infrastructure.repositories;

import com.quyvx.main_server.domain.aggregate_models.manager_aggregate.Manager;
import com.quyvx.main_server.domain.repositories.IManagerRepository;
import com.quyvx.main_server.infrastructure.entities.ManagerEntity;
import com.quyvx.main_server.infrastructure.entity_mappers.ManagerEntityMapper;
import com.quyvx.main_server.infrastructure.jpa_repositories.ManagerJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ManagerRepository implements IManagerRepository {
    private final ManagerJpaRepository managerJpaRepository;
    private final ManagerEntityMapper mapper;
    @Override
    public Manager save(Manager model) {
        ManagerEntity entity = mapper.modelToEntity(model);
        entity = managerJpaRepository.save(entity);
        return mapper.entityToModel(entity);
    }

    @Override
    public void delete(Long id) {
        managerJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Manager> findById(Long id) {
        return managerJpaRepository.findById(id).map(mapper::entityToModel);
    }
}
