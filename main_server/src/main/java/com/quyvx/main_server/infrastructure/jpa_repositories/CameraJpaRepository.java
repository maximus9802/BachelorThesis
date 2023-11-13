package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.infrastructure.entities.CameraEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraJpaRepository extends BaseJpaRepository<CameraEntity, Long> {
}
