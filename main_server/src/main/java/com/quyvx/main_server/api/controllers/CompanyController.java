package com.quyvx.main_server.api.controllers;

import an.awesome.pipelinr.Pipeline;
import com.quyvx.main_server.api.application.commands.company.delete_company_command.DeleteCompanyCommand;
import com.quyvx.main_server.api.application.commands.company.update_company_command.UpdateCompanyCommand;
import com.quyvx.main_server.api.application.models.company.CompanySummary;
import com.quyvx.main_server.api.application.queries.company.ICompanyQueriesService;
import com.quyvx.main_server.api.application.services.company.CompanyService;
import com.quyvx.main_server.api.dto.company.CreateUpdateCompanyReqDto;
import com.quyvx.main_server.shared.constants.RoleEnum;
import com.quyvx.main_server.shared.exceptions.NotFoundException;
import com.quyvx.main_server.shared.libs.application.dto.UserDetail;
import com.quyvx.main_server.shared.libs.application.security.SecurityService;
import com.quyvx.main_server.shared.libs.shared.helpers.MessageHelper;
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
    private final Pipeline pipeline;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN_ADMIN')")
    public List<CompanySummary> getDetailCompanyById() {
        return companyQueriesService.listAllCompany();
    }

    @GetMapping("/{companyId}")
    @PreAuthorize("hasAnyAuthority('ADMIN_ADMIN', 'COMPANY')")
    public CompanySummary getDetailCompanyById(@PathVariable("companyId") Long companyId){
        UserDetail userDetail = securityService.getUserDetail();
        CompanySummary companySummary = companyQueriesService.getDetailCompanyById(companyId)
                .orElseThrow(() -> new NotFoundException(MessageHelper.getMessage("company_not_found")));
        companyService.canAccessCompanyResource(userDetail, companyId);
        return companySummary;
    }

    @GetMapping("/{permanentLink}")
    public CompanySummary getDetailCompanyByPermanentLink(@PathVariable("permanentLink") String permanentLink) {
        // TODO: get company summary by permanent link
        return null;
    }
    @PutMapping("{companyId}")
    @PreAuthorize("hasAnyAuthority('ADMIN_ADMIN, COMPANY')")
    public void updateCompany(@PathVariable("companyId") Long companyId, @RequestBody CreateUpdateCompanyReqDto request){
        UserDetail userDetail = securityService.getUserDetail();
        CompanySummary companySummary = companyQueriesService.getDetailCompanyById(companyId)
                .orElseThrow(() -> new NotFoundException(MessageHelper.getMessage("company_not_found")));
        companyService.canAccessCompanyResource(userDetail, companyId);
        UpdateCompanyCommand command = UpdateCompanyCommand.builder()
                .companyId(companyId)
                .identityId(companySummary.getIdentityId())
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .build();
        if (userDetail.getRoles().contains(RoleEnum.ADMIN_ADMIN.name())) {
            command.setCompanyName(request.getCompanyName());
            command.setPermanentLink(request.getPermanentLink());
        } else {
            command.setCompanyName(companySummary.getCompanyName());
            command.setPermanentLink(companySummary.getPermanentLink());
        }
        log.info("----- CompanyController: Identity {} is updating company {}", userDetail.getId(), companyId);
        pipeline.send(command);
    }

    @DeleteMapping("/{companyId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteCompany(@PathVariable("companyId") Long companyId) {
        UserDetail userDetail = securityService.getUserDetail();
        companyService.canAccessCompanyResource(userDetail, companyId);
        DeleteCompanyCommand command = DeleteCompanyCommand.builder()
                .companyId(companyId)
                .build();
        log.info("----- CompanyController: Identity {} is deleting company {}", userDetail.getId(), companyId);
        pipeline.send(command);
    }
}