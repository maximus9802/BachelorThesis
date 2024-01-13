package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.infrastructure.entities.StatusParkingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusParkingJpaRepository extends BaseJpaRepository<StatusParkingEntity, Long> {

    @Query(value = "SELECT sp.id " +
            "FROM status_parking AS sp " +
            "WHERE sp.type = :type ", nativeQuery = true)
    Optional<Long> findStatusParkingIdByStatusParkingType(@Param("type") String type);
}
