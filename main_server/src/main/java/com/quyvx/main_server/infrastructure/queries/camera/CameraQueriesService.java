package com.quyvx.main_server.infrastructure.queries.camera;

import com.quyvx.main_server.api.application.queries.camera.ICameraQueriesService;
import com.quyvx.main_server.infrastructure.jpa_repositories.CameraJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CameraQueriesService implements ICameraQueriesService {
    private final CameraJpaRepository cameraJpaRepository;
}
