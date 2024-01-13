package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.infrastructure.entities.AuthenticationHistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationHistoryJpaRepository extends BaseJpaRepository<AuthenticationHistoryEntity, Long> {

    @Query(value = "SELECT ah.id " +
            "FROM authentication_history AS ah " +
            "JOIN status_parking AS sp ON ah.status_parking_id = sp.id " +
            "WHERE ah.license_plate_code = :licensePlate AND sp.type = 'PARKING' ", nativeQuery = true)
    Long getAuthenticationHistoryIdInParkingByLicensePlate(@Param("licensePlate") String licensePlate);
}
