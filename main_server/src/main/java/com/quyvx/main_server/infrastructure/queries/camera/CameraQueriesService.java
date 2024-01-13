package com.quyvx.main_server.infrastructure.queries.camera;

import com.quyvx.main_server.api.application.models.camera.CameraInfo;
import com.quyvx.main_server.api.application.queries.camera.ICameraQueriesService;
import com.quyvx.main_server.infrastructure.jpa_repositories.CameraJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CameraQueriesService implements ICameraQueriesService {
    private final CameraJpaRepository cameraJpaRepository;

    @Override
    public Optional<CameraInfo> getCameraInfoByCameraUUID(String cameraUUID) {
        return cameraJpaRepository.getCameraInfoByCameraUUID(cameraUUID);
    }
}
