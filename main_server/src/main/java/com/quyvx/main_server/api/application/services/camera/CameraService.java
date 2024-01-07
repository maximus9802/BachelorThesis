package com.quyvx.main_server.api.application.services.camera;

import com.quyvx.main_server.api.application.models.camera.CameraInfo;
import com.quyvx.main_server.api.application.queries.camera.ICameraQueriesService;
import com.quyvx.main_server.shared.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CameraService {
    private final ICameraQueriesService cameraQueriesService;

    public CameraInfo checkCameraIsValid(String cameraUUID) {
        Optional<CameraInfo> cameraInfo = cameraQueriesService.getCameraInfoByCameraUUID(cameraUUID);
        if (cameraInfo.isEmpty()) {
            throw new NotFoundException("Camera is valid!");
        }
        return cameraInfo.get();
    }
}
