package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.infrastructure.entities.AuthenticationHistoryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationHistoryJpaRepository extends BaseJpaRepository<AuthenticationHistoryEntity, Long> {
}
