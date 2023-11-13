package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.infrastructure.entities.CompanyEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyJpaRepository extends BaseJpaRepository<CompanyEntity, Long> {
}
