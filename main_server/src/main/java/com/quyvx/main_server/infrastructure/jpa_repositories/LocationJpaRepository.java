package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.api.application.models.location.LocationDetails;
import com.quyvx.main_server.infrastructure.entities.LocationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationJpaRepository extends BaseJpaRepository<LocationEntity, Long> {

    @Query(value = "SELECT l.id as id, l.name as name, l.address as address, l.permanent_link as permanentLink, " +
            "l.company_id as companyId " +
            "FROM locations as l " +
            "WHERE l.id = :locationId, l.is_deleted = false ", nativeQuery = true)
    Optional<LocationDetails> getLocationDetail(@Param("locationId") Long locationId);

    @Query(value = "SELECT l.id as id, l.name as name, l.address as address, l.permanent_link as permanentLink, " +
            "l.company_id as companyId " +
            "FROM locations as l " +
            "INNER JOIN companies as c ON l.company_id = c.id " +
            "WHERE c.id = :companyId AND l.is_deleted = false AND " +
            "IF(:search IS NULL, TRUE, (" +
            "LOWER(l.name) LIKE CONCAT('%', LOWER(:search), '%') OR " +
            "LOWER(l.address) LIKE CONCAT('%', LOWER(:search), '%') OR " +
            "LOWER(l.permanent_link) LIKE CONCAT('%', LOWER(:search), '%') " +
            ")) " +
            "ORDER BY l.id " +
            "LIMIT :limit OFFSET :offset" , nativeQuery = true)
    List<LocationDetails> listLocationsByCompanyIdAndSearchWithPagination(@Param("companyId") Long companyId,
                                                                          @Param("offset") Long offset,
                                                                          @Param("limit") Integer limit,
                                                                          @Param("search") String search);

    @Query(value = "SELECT COUNT(l.id) " +
            "FROM locations as l " +
            "INNER JOIN companies as c ON l.company_id = c.id " +
            "WHERE c.id = :companyId AND l.is_deleted = false AND " +
            "IF(:search IS NULL, TRUE, (" +
            "LOWER(l.name) LIKE CONCAT('%', LOWER(:search), '%') OR " +
            "LOWER(l.address) LIKE CONCAT('%', LOWER(:search), '%') OR " +
            "LOWER(l.permanent_link) LIKE CONCAT('%', LOWER(:search), '%') " +
            ")) ", nativeQuery = true)
    Long countLocationsByCompanyIdAndSearchWithPagination(@Param("companyId") Long companyId,
                                                          @Param("search") String search);
}
