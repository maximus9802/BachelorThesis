package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.infrastructure.entities.ManagerEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerJpaRepository extends BaseJpaRepository<ManagerEntity, Long>{
}
