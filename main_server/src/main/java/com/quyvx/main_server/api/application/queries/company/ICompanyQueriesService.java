package com.quyvx.main_server.api.application.queries.company;

import com.quyvx.main_server.api.application.models.company.CompanySummary;

import java.util.List;

public interface ICompanyQueriesService {

    List<CompanySummary> listAllCompany();


    CompanySummary getDetailCompanyById(Long companyId);
}
