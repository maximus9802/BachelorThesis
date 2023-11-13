package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.infrastructure.entities.LocationEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationJpaRepository extends BaseJpaRepository<LocationEntity, Long> {
}
