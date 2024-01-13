package com.quyvx.main_server.api.controllers;

import com.quyvx.main_server.api.application.models.company.CompanySummary;
import com.quyvx.main_server.api.application.queries.company.ICompanyQueriesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
@Slf4j
@AllArgsConstructor
public class CompanyController {
    private final ICompanyQueriesService companyQueriesService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<CompanySummary> getDetailCompanyById() {
        return null;
    }
}
