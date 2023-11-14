package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.infrastructure.entities.StatusParkingEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusParkingJpaRepository extends BaseJpaRepository<StatusParkingEntity, Long> {
}
