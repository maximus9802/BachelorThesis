package com.quyvx.main_server.infrastructure.repositories;

import com.quyvx.main_server.domain.aggregate_models.location_aggregate.Location;
import com.quyvx.main_server.domain.repositories.ILocationRepository;
import com.quyvx.main_server.infrastructure.entities.LocationEntity;
import com.quyvx.main_server.infrastructure.entity_mappers.LocationEntityMapper;
import com.quyvx.main_server.infrastructure.jpa_repositories.LocationJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class LocationRepository implements ILocationRepository {
    private final LocationJpaRepository locationJpaRepository;
    private final LocationEntityMapper mapper;
    @Override
    public Location save(Location model) {
        LocationEntity entity = mapper.modelToEntity(model);
        entity = locationJpaRepository.save(entity);
        return mapper.entityToModel(entity);
    }

    @Override
    public void delete(Long id) {
        locationJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Location> findById(Long id) {
        return locationJpaRepository.findById(id).map(mapper::entityToModel);
    }
}
