package com.quyvx.main_server.api.controllers;

import an.awesome.pipelinr.Pipeline;
import com.quyvx.main_server.api.application.commands.log.login.CreateLogInParkingCommand;
import com.quyvx.main_server.api.application.commands.log.logout.CreateLogOutParkingCommand;
import com.quyvx.main_server.api.application.models.authentication_history.AuthenticationHistoryDetail;
import com.quyvx.main_server.api.application.models.authentication_history.AuthenticationHistorySummary;
import com.quyvx.main_server.api.application.models.camera.CameraInfo;
import com.quyvx.main_server.api.application.queries.authentication_history.AuthenticationHistoryQueries;
import com.quyvx.main_server.api.application.services.camera.CameraService;
import com.quyvx.main_server.api.application.services.log.LogService;
import com.quyvx.main_server.api.dto.log.AuthenticationParkingReq;
import com.quyvx.main_server.api.dto.log.AuthenticationParkingRes;
import com.quyvx.main_server.shared.constants.ProjectConstants;
import com.quyvx.main_server.shared.dto.PaginationResponse;
import com.quyvx.main_server.shared.services.GCSService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RequestMapping("authentication")
@Slf4j
@RestController
public class LogController {
    private final CameraService cameraService;
    private final Pipeline pipeline;
    private final LogService logService;
    private final AuthenticationHistoryQueries authenticationHistoryQueries;

    @PostMapping("/log")
    public AuthenticationParkingRes requestParking(@RequestParam("image") MultipartFile image,
                                                   @RequestParam("cameraUUID") String cameraUUID ) {
        CameraInfo cameraInfo = cameraService.checkCameraIsValid(cameraUUID);
        String imageUrl = logService.uploadImageToCloud(image);
        Long handleCode = 0L;
        if (Objects.equals(cameraInfo.getTypeAuth(), ProjectConstants.AUTHENTICATION_LOGIN)) {
            CreateLogInParkingCommand command = CreateLogInParkingCommand.builder()
                    .cameraId(cameraInfo.getId())
                    .imageUrl(imageUrl)
                    .build();
            log.info("----- Camera id {} request login parking", cameraInfo.getId());
            if (ObjectUtils.isNotEmpty(pipeline.send(command))) {
                handleCode = ProjectConstants.HANDLE_CODE_ACCEPT;
                log.info("----- Authenticate successfully!");
            }
            else handleCode = ProjectConstants.HANDLE_CODE_ERROR;
        } else if (Objects.equals(cameraInfo.getTypeAuth(), ProjectConstants.AUTHENTICATION_LOGOUT)) {
            CreateLogOutParkingCommand command = CreateLogOutParkingCommand.builder()
                    .cameraId(cameraInfo.getId())
                    .imageUrl(imageUrl)
                    .build();
            log.info("----- Camera id {} request logout parking", cameraInfo.getId());
            if (ObjectUtils.isNotEmpty(pipeline.send(command))) {
                handleCode = ProjectConstants.HANDLE_CODE_ACCEPT;
                log.info("----- Authenticate successfully!");
            }
            else handleCode = ProjectConstants.HANDLE_CODE_ERROR;
        }
        return  AuthenticationParkingRes.builder()
                .handleCode(handleCode)
                .build();

    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMPANY')")
    public PaginationResponse<List<AuthenticationHistorySummary>> getAllAuthenticationParking(@RequestParam("companyId") Long companyId,
                                                                                              @RequestParam(required = false, defaultValue = "0") Long offset,
                                                                                              @RequestParam(required = false, defaultValue = "10") Integer limit ) {
        return authenticationHistoryQueries.getAuthenticationHistory(offset, limit, companyId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMPANY')")
    public AuthenticationHistoryDetail getAuthenticationHistoryDetail(@PathVariable("id") Long authenticationHistoryId) {
        return authenticationHistoryQueries.getAuthenticationHistoryDetail(authenticationHistoryId);
    }
}
