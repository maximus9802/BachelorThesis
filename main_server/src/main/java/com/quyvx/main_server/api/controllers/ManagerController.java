package com.quyvx.main_server.api.controllers;

import an.awesome.pipelinr.Pipeline;
import com.quyvx.main_server.api.application.commands.manager.create_manager_command.CreateManagerCommand;
import com.quyvx.main_server.api.dto.manager.CreateManagerReqDto;
import com.quyvx.main_server.shared.libs.application.dto.UserDetail;
import com.quyvx.main_server.shared.libs.application.security.SecurityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manager")
@AllArgsConstructor
@Slf4j
public class ManagerController {
    private final Pipeline pipeline;
    private final SecurityService securityService;

    @RequestMapping("/create_manager")
    @PreAuthorize("hasAnyAuthority('COMPANY', 'ADMIN_ADMIN')")
    public void createManager(@RequestBody CreateManagerReqDto request) {
        UserDetail userDetail = securityService.getUserDetail();
        CreateManagerCommand command = CreateManagerCommand.builder()
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .dateOfBirth(request.getDateOfBirth())
                .postCode(request.getPostCode())
                .address(request.getAddress())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .locationsManage(request.getLocationsManage())
                .build();
        log.info("----- Identity id {} is creating manager with login id {}", userDetail.getId(), request.getLoginId());
        pipeline.send(command);
    }
}
