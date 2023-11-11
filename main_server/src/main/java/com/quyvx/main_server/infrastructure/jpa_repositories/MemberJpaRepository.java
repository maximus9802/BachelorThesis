package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.infrastructure.entities.MemberEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpaRepository extends BaseJpaRepository<MemberEntity, Long> {
}
