package com.quyvx.main_server.api.application.models.camera;

public interface CameraInfo {
    Long getId();

    String getLocationId();

    String getName();

    String getTypeAuth();

    String getCameraUUID();

    Boolean isDeleted();
}
