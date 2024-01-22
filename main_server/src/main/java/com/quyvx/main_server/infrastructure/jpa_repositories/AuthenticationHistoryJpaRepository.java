package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.api.application.models.authentication_history.AuthenticationHistoryDetail;
import com.quyvx.main_server.api.application.models.authentication_history.AuthenticationHistorySummary;
import com.quyvx.main_server.infrastructure.entities.AuthenticationHistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthenticationHistoryJpaRepository extends BaseJpaRepository<AuthenticationHistoryEntity, Long> {

    @Query(value = "SELECT ah.id " +
            "FROM authentication_history AS ah " +
            "JOIN status_parking AS sp ON ah.status_parking_id = sp.id " +
            "WHERE ah.license_plate_code = :licensePlate AND sp.type = 'PARKING' ", nativeQuery = true)
    Long getAuthenticationHistoryIdInParkingByLicensePlate(@Param("licensePlate") String licensePlate);

    @Query(value = "SELECT ah.id as id, t1.location_name as location, al1.date as dateLogIn, al1.time as timeLogIn, " +
            "al2.date as dateLogOut, al2.time as timeLogOut, sp.name as statusParking, ah.license_plate_code as licensePlate " +
            "FROM authentication_history AS ah " +
            "LEFT JOIN status_parking AS sp ON ah.status_parking_id = sp.id " +
            "LEFT JOIN authentication_logs AS al1 ON ah.authen_login_id = al1.id " +
            "LEFT JOIN authentication_logs AS al2 ON ah.authen_logout_id = al2.id " +
            "LEFT JOIN ( SELECT l.address as location_name, c.id as camera_id " +
            "FROM cameras AS c " +
            "LEFT JOIN locations AS l ON c.location_id = l.id " +
            "LEFT JOIN companies AS com ON l.company_id = com.id " +
            "WHERE com.id = :companyId ) AS t1 ON al1.camera_id = t1.camera_id " +
            "LIMIT :limit OFFSET :offset ", nativeQuery = true)
    List<AuthenticationHistorySummary> getAuthenticationHistoryWithPagination(@Param("offset") Long offset,
                                                                              @Param("limit") Integer integer,
                                                                              @Param("companyId") Long companyId);

    @Query(value = "SELECT COUNT(*) " +
            "FROM authentication_history AS ah " +
            "LEFT JOIN authentication_logs AS al ON ah.authen_login_id = al.id " +
            "LEFT JOIN cameras AS c ON al.camera_id = c.id " +
            "LEFT JOIN locations AS l ON c.location_id = l.id " +
            "LEFT JOIN companies as com ON l.company_id = com.id " +
            "WHERE com.id = :companyId ", nativeQuery = true)
    Long countAuthenticationHistory(@Param("companyId") Long companyId);

    @Query(value = "SELECT l.address AS location, al1.date AS dateLogIn, al1.time AS timeLogIn, al1.evidence_link AS evidenceLogIn, " +
            "al2.date AS dateLogOut, al2.time AS timeLogOut, al2.evidence_link AS evidenceLogOut, " +
            "ah.duration AS duration, sp.name AS statusParking, ah.license_plate_code AS licensePlate " +
            "FROM authentication_history AS ah " +
            "LEFT JOIN status_parking AS sp ON ah.status_parking_id = sp.id " +
            "LEFT JOIN authentication_logs AS al1 ON ah.authen_login_id = al1.id " +
            "LEFT JOIN authentication_logs AS al2 ON ah.authen_logout_id = al2.id " +
            "LEFT JOIN cameras AS c ON al1.camera_id = c.id " +
            "LEFT JOIN locations AS l ON c.location_id = l.id " +
            "WHERE ah.id = :id ", nativeQuery = true)
    Optional<AuthenticationHistoryDetail> getAuthenticationHistoryDetail(@Param("id") Long id);
}
