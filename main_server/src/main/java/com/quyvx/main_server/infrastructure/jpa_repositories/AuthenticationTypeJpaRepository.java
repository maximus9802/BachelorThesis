package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.infrastructure.entities.AuthenticationTypeEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationTypeJpaRepository extends BaseJpaRepository<AuthenticationTypeEntity, Long> {
}
