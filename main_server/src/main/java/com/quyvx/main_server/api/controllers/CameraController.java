package com.quyvx.main_server.api.controllers;

import an.awesome.pipelinr.Pipeline;
import com.quyvx.main_server.api.application.commands.camera.create_camera_command.CreateCameraCommand;
import com.quyvx.main_server.api.dto.camera.CreateCameraReqDto;
import com.quyvx.main_server.shared.libs.application.dto.UserDetail;
import com.quyvx.main_server.shared.libs.application.security.SecurityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("camera")
@Slf4j
public class CameraController {
    private final Pipeline pipeline;
    private final SecurityService securityService;

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN_ADMIN', 'COMPANY')")
    public String createCamera(@RequestBody CreateCameraReqDto request){
        UserDetail userDetail = securityService.getUserDetail();
        //check permission to create new camera
        CreateCameraCommand command = CreateCameraCommand.builder()
                .locationId(request.getLocationId())
                .name(request.getName())
                .typeAuth(request.getTypeAuth())
                .build();
        log.info("----- Identity id {} is creating camera with name {}", userDetail.getId(), request.getName());
        return pipeline.send(command);
    }
}
