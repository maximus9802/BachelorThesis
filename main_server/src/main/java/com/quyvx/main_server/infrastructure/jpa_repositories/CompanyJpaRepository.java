package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.api.application.models.company.CompanySummary;
import com.quyvx.main_server.infrastructure.entities.CompanyEntity;
import org.apache.el.util.Validation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyJpaRepository extends BaseJpaRepository<CompanyEntity, Long> {

    @Query(value = "SELECT c.id as id, c.identity_id as identityId, c.company_name as companyName, c.address as address, " +
            "c.phone_number as phoneNumber, c.email as email, c.permanent_link as permanentLink " +
            "FROM company as c " +
            "WHERE c.id = :companyId ", nativeQuery = true)
    Optional<CompanySummary> getDetailCompanyById(@Param("companyId") Long companyId);
}
