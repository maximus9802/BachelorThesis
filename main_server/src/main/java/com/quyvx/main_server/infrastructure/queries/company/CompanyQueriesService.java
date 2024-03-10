package com.quyvx.main_server.infrastructure.queries.company;

import com.quyvx.main_server.api.application.models.company.CompanySummary;
import com.quyvx.main_server.api.application.queries.company.ICompanyQueriesService;
import com.quyvx.main_server.infrastructure.jpa_repositories.CompanyJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyQueriesService implements ICompanyQueriesService {

    private final CompanyJpaRepository companyJpaRepository;

    @Override
    public List<CompanySummary> listAllCompany() {
        return null;
    }

    @Override
    public Optional<CompanySummary> getDetailCompanyById(Long companyId) {
        return companyJpaRepository.getDetailCompanyById(companyId);
    }
}
