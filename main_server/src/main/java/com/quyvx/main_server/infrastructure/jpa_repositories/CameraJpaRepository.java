package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.api.application.models.camera.CameraInfo;
import com.quyvx.main_server.infrastructure.entities.CameraEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CameraJpaRepository extends BaseJpaRepository<CameraEntity, Long> {

    @Query(value = "SELECT c.id AS id, c.location_id AS locationId, c.name AS name, authT.type as typeAuth, c.camera_uuid AS cameraUUID, c.is_deleted AS isDeleted " +
            "FROM cameras AS c " +
            "LEFT JOIN authentication_type AS authT ON c.type_authen_id = authT.id " +
            "WHERE c.camera_uuid = :cameraUUID ", nativeQuery = true)
    Optional<CameraInfo> getCameraInfoByCameraUUID(@Param("cameraUUID") String cameraUUID);
}
