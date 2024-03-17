package com.quyvx.main_server.api.controllers;

import an.awesome.pipelinr.Pipeline;
import com.quyvx.main_server.api.application.commands.location.create_location_command.CreateLocationCommand;
import com.quyvx.main_server.api.application.commands.location.delete_location_command.DeleteLocationCommand;
import com.quyvx.main_server.api.application.commands.location.update_location_command.UpdateLocationCommand;
import com.quyvx.main_server.api.application.models.location.LocationDetails;
import com.quyvx.main_server.api.application.queries.location.ILocationQueriesService;
import com.quyvx.main_server.api.application.queries.location.LocationQueries;
import com.quyvx.main_server.api.application.services.company.CompanyService;
import com.quyvx.main_server.api.dto.location.CreateUpdateLocationReqDto;
import com.quyvx.main_server.domain.aggregate_models.location_aggregate.Location;
import com.quyvx.main_server.shared.dto.PaginationResponse;
import com.quyvx.main_server.shared.exceptions.NotFoundException;
import com.quyvx.main_server.shared.libs.application.dto.UserDetail;
import com.quyvx.main_server.shared.libs.application.security.SecurityService;
import com.quyvx.main_server.shared.libs.shared.helpers.MessageHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("locations")
@RestController
@AllArgsConstructor
@Slf4j
public class LocationController {
    private final Pipeline pipeline;
    private final SecurityService securityService;
    private final CompanyService companyService;
    private final ILocationQueriesService locationQueriesService;
    private final LocationQueries locationQueries;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN_ADMIN', 'COMPANY')")
    public PaginationResponse<List<LocationDetails>> listLocations(@RequestParam("companyId") Long companyId,
                                                  @RequestParam(required = false, defaultValue = "0") Long offset,
                                                  @RequestParam(required = false, defaultValue = "10") Integer limit,
                                                  @RequestParam(required = false, defaultValue = "") String search) {
        return locationQueries.listLocationsByCompanyIdAndSearchWithPagination(companyId, offset, limit, search);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN_ADMIN', 'COMPANY')")
    public Location createLocation(@RequestBody CreateUpdateLocationReqDto request) {
        UserDetail userDetail = securityService.getUserDetail();

        //check permission to create new location
        companyService.canAccessCompanyResource(userDetail, request.getCompanyId());
        CreateLocationCommand command = CreateLocationCommand.builder()
                .companyId(request.getCompanyId())
                .locationName(request.getLocationName())
                .address(request.getAddress())
                .permanentLink(request.getPermanentLink())
                .build();
        log.info("----- Identity {} is creating location with permanent link is {}", userDetail.getId(), request.getPermanentLink());
        return pipeline.send(command);
    }

    @PutMapping("/{locationId}")
    @PreAuthorize("hasAnyAuthority('ADMIN_ADMIN', 'COMPANY')")
    public Long updateLocation(@PathVariable("locationId") Long locationId, @RequestBody CreateUpdateLocationReqDto request) {
        UserDetail userDetail = securityService.getUserDetail();
        LocationDetails locationDetails = locationQueriesService.getLocationDetail(locationId).orElseThrow(
                () -> new NotFoundException(MessageHelper.getMessage("location_not_found")));
        companyService.canAccessCompanyResource(userDetail, locationDetails.getCompanyId());
        UpdateLocationCommand command = UpdateLocationCommand.builder()
                .locationId(locationId)
                .name(request.getLocationName())
                .companyId(locationDetails.getCompanyId())
                .address(request.getAddress())
                .build();
        if (userDetail.getRoles().contains("ADMIN_ADMIN")) {
            command.setPermanentLink(request.getPermanentLink());
        } else {
            command.setPermanentLink(locationDetails.getPermanentLink());
        }
        return pipeline.send(command);
    }

    @GetMapping("/{locationId}")
    @PreAuthorize("hasAnyAuthority('ADMIN_ADMIN', 'COMPANY')")
    public LocationDetails getLocationDetail(@PathVariable("locationId") Long locationId) {
        UserDetail userDetail = securityService.getUserDetail();
        LocationDetails locationDetails = locationQueriesService.getLocationDetail(locationId).orElseThrow(
                () -> new NotFoundException(MessageHelper.getMessage("location_not_found")));
        companyService.canAccessCompanyResource(userDetail, locationDetails.getCompanyId());
        return locationDetails;
    }

    @DeleteMapping("/{locationId}")
    @PreAuthorize("hasAnyAuthority('ADMIN_ADMIN', 'COMPANY')")
    public void deleteLocation(@PathVariable("locationId") Long locationId) {
        UserDetail userDetail = securityService.getUserDetail();
        LocationDetails locationDetails = locationQueriesService.getLocationDetail(locationId).orElseThrow(
                () -> new NotFoundException(MessageHelper.getMessage("location_not_found")));
        companyService.canAccessCompanyResource(userDetail, locationDetails.getCompanyId());
        DeleteLocationCommand command = DeleteLocationCommand.builder()
                .locationId(locationId)
                .build();
        pipeline.send(command);
    }
}
