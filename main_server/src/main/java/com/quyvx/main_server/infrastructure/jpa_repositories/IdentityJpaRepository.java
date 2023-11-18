package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.api.application.models.identity.IdentityLogin;
import com.quyvx.main_server.infrastructure.entities.IdentityEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdentityJpaRepository extends BaseJpaRepository<IdentityEntity, Long> {

    @Query(value = "SELECT i.id, i.login_id AS loginId, i.password AS password, m.id as memberId " +
            "FROM identities AS i " +
            "LEFT JOIN members AS m ON m.identity_id = i.id " +
            "LEFT JOIN companies AS c ON c.identity_id = i.id " +
            "WHERE i.login_id = :loginId AND i.is_deleted = false", nativeQuery = true)
    Optional<IdentityLogin> getIdentityByLoginId(@Param("loginId") String loginId);
}
