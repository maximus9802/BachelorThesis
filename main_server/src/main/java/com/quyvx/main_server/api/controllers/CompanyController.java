package com.quyvx.main_server.api.controllers;

import com.electronwill.nightconfig.core.conversion.Path;
import com.quyvx.main_server.api.application.models.company.CompanySummary;
import com.quyvx.main_server.api.application.queries.company.ICompanyQueriesService;
import com.quyvx.main_server.api.application.services.company.CompanyService;
import com.quyvx.main_server.shared.libs.application.dto.UserDetail;
import com.quyvx.main_server.shared.libs.application.security.SecurityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@Slf4j
@AllArgsConstructor
public class CompanyController {
    private final ICompanyQueriesService companyQueriesService;
    private final CompanyService companyService;
    private final SecurityService securityService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN_ADMIN')")
    public List<CompanySummary> getDetailCompanyById() {
        return companyQueriesService.listAllCompany();
    }

    @GetMapping("/{companyId}")
    @PreAuthorize("hasAnyAuthority('ADMIN_ADMIN', 'COMPANY')")
    public CompanySummary getDetailCompanyById(@PathVariable("companyId") Long companyId){
        UserDetail userDetail = securityService.getUserDetail();
        companyService.canAccessCompanyResource(userDetail, companyId);
        return companyQueriesService.getDetailCompanyById(companyId);
    }

    @GetMapping("/{permanentLink}")
    public CompanySummary getDetailCompanyByPermanentLink(@PathVariable("permanentLink") String permanentLink) {
        // TODO: get company summary by permanent link
        return null;
    }
    @PutMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN, COMPANY')")
    public void updateCompany(){
        // TODO: update company info
    }

    @DeleteMapping("/{companyId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteCompany(@PathVariable("companyId") Long companyId) {
        // TODO: delete company by id
    }
}