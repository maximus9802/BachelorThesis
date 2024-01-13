package com.quyvx.main_server.api.controllers;

import an.awesome.pipelinr.Pipeline;
import com.quyvx.main_server.api.application.commands.admin.create_company_command.CreateCompanyCommand;
import com.quyvx.main_server.api.dto.company.CreateCompanyReqDto;
import com.quyvx.main_server.domain.aggregate_models.company_aggregate.Company;
import com.quyvx.main_server.shared.libs.application.dto.UserDetail;
import com.quyvx.main_server.shared.libs.application.security.SecurityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@Slf4j
@AllArgsConstructor
public class AdminController {
    private final Pipeline pipeline;
    private final SecurityService securityService;

    @PostMapping("/register_company")
//    @PreAuthorize("hasAnyAuthority('USER')")
    public Company registerCompany(@RequestBody CreateCompanyReqDto request) {
        UserDetail userDetail = securityService.getUserDetail();
        CreateCompanyCommand command = CreateCompanyCommand.builder()
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .companyName(request.getCompanyName())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .permanentLink(request.getPermanentLink())
                .build();
        log.info("----- Identity {} is creating company that has loginId {}", userDetail.getId(), request.getLoginId());
        return pipeline.send(command);
    }


}
