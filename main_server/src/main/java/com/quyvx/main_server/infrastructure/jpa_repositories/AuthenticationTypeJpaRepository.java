package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.infrastructure.entities.AuthenticationTypeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationTypeJpaRepository extends BaseJpaRepository<AuthenticationTypeEntity, Long> {

    @Query(value = "SELECT at.id " +
            "FROM authentication_type as at " +
            "WHERE at.type = :typeName ", nativeQuery = true)
    Long getAuthenticationTypeIdByTypeName(@Param("typeName") String typeName);

    @Query(value = "SELECT at.type " +
            "FROM authentication_type as at " +
            "WHERE at.id = :id", nativeQuery = true)
    String getAuthenticationTypeById(@Param("id") Long id);
}
