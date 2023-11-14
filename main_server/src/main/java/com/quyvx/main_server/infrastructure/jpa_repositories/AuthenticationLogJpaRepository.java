package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.infrastructure.entities.AuthenticationLogEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationLogJpaRepository extends BaseJpaRepository<AuthenticationLogEntity, Long> {
}
