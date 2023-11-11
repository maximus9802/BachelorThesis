package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.infrastructure.entities.IdentityEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityJpaRepository extends BaseJpaRepository<IdentityEntity, Long> {
}
