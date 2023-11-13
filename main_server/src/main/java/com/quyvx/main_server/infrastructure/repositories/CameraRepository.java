package com.quyvx.main_server.infrastructure.repositories;

import com.quyvx.main_server.domain.aggregate_models.camera_aggregate.Camera;
import com.quyvx.main_server.domain.repositories.ICameraRepository;
import com.quyvx.main_server.infrastructure.entities.CameraEntity;
import com.quyvx.main_server.infrastructure.entity_mappers.CameraEntityMapper;
import com.quyvx.main_server.infrastructure.jpa_repositories.CameraJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CameraRepository implements ICameraRepository {
    private final CameraJpaRepository cameraJpaRepository;
    private final CameraEntityMapper mapper;
    @Override
    public Camera save(Camera model) {
        CameraEntity entity = mapper.modelToEntity(model);
        entity = cameraJpaRepository.save(entity);
        return mapper.entityToModel(entity);
    }

    @Override
    public void delete(Long id) {
        cameraJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Camera> findById(Long id) {
        return cameraJpaRepository.findById(id).map(mapper::entityToModel);
    }
}
