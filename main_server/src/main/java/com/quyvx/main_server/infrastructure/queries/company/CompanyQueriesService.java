package com.quyvx.main_server.infrastructure.queries.company;

import com.quyvx.main_server.api.application.models.company.CompanySummary;
import com.quyvx.main_server.api.application.queries.company.ICompanyQueriesService;
import com.quyvx.main_server.infrastructure.jpa_repositories.CompanyJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyQueriesService implements ICompanyQueriesService {

    private final CompanyJpaRepository companyJpaRepository;

    @Override
    public List<CompanySummary> listAllCompany() {
        return null;
    }
}
