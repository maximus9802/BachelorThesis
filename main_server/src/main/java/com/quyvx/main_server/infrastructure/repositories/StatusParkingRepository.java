package com.quyvx.main_server.infrastructure.repositories;

import com.quyvx.main_server.domain.aggregate_models.status_parking_aggregate.StatusParking;
import com.quyvx.main_server.domain.repositories.IStatusParkingRepository;
import com.quyvx.main_server.infrastructure.entities.StatusParkingEntity;
import com.quyvx.main_server.infrastructure.entity_mappers.StatusParkingEntityMapper;
import com.quyvx.main_server.infrastructure.jpa_repositories.StatusParkingJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class StatusParkingRepository implements IStatusParkingRepository {
    private final StatusParkingJpaRepository statusParkingJpaRepository;
    private final StatusParkingEntityMapper mapper;

    @Override
    public StatusParking save(StatusParking model) {
        StatusParkingEntity entity = mapper.modelToEntity(model);
        entity = statusParkingJpaRepository.save(entity);
        return mapper.entityToModel(entity);
    }

    @Override
    public void delete(Long id) {
        statusParkingJpaRepository.deleteById(id);
    }

    @Override
    public Optional<StatusParking> findById(Long id) {
        return statusParkingJpaRepository.findById(id).map(mapper::entityToModel);
    }
}
