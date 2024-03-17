package com.quyvx.main_server.api.application.queries.company;

import com.quyvx.main_server.api.application.models.company.CompanySummary;

import java.util.List;
import java.util.Optional;

public interface ICompanyQueriesService {

    List<CompanySummary> listAllCompany();


    Optional<CompanySummary> getDetailCompanyById(Long companyId);
}
