package com.quyvx.main_server.api.application.queries.camera;

import com.quyvx.main_server.api.application.models.camera.CameraInfo;

import java.util.Optional;

public interface ICameraQueriesService {
    Optional<CameraInfo> getCameraInfoByCameraUUID(String cameraUUID);
}
