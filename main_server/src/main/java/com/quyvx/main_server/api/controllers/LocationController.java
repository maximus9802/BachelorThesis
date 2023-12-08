package com.quyvx.main_server.api.controllers;

import an.awesome.pipelinr.Pipeline;
import com.quyvx.main_server.api.application.commands.location.create_location_command.CreateLocationCommand;
import com.quyvx.main_server.api.dto.location.CreateLocationReqDto;
import com.quyvx.main_server.domain.aggregate_models.location_aggregate.Location;
import com.quyvx.main_server.shared.libs.application.dto.UserDetail;
import com.quyvx.main_server.shared.libs.application.security.SecurityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("location")
@RestController
@AllArgsConstructor
@Slf4j
public class LocationController {
    private final Pipeline pipeline;
    private final SecurityService securityService;

    @RequestMapping("/new_location")
    @PreAuthorize("hasAnyAuthority('ADMIN_ADMIN', 'COMPANY')")
    public Location createLocation(@RequestBody CreateLocationReqDto request) {
        UserDetail userDetail = securityService.getUserDetail();

        //check permission to create new location

        CreateLocationCommand command = CreateLocationCommand.builder()
                .companyId(request.getCompanyId())
                .locationName(request.getLocationName())
                .address(request.getAddress())
                .permanentLink(request.getPermanentLink())
                .build();
        log.info("----- Identity {} is creating location with permanent link is {}", userDetail.getId(), request.getPermanentLink());
        return pipeline.send(command);
    }
}
