package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.infrastructure.entities.RoleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends BaseJpaRepository<RoleEntity, Long> {

    @Query(value = "SELECT r.id as id " +
            "FROM roles as r " +
            "WHERE r.type = :roleType ", nativeQuery = true)
    Long getRoleIdByRoleType(@Param("roleType") String roleType);
}
