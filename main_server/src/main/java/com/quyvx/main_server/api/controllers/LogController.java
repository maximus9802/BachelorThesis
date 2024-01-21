package com.quyvx.main_server.api.controllers;

import an.awesome.pipelinr.Pipeline;
import com.quyvx.main_server.api.application.commands.log.login.CreateLogInParkingCommand;
import com.quyvx.main_server.api.application.commands.log.logout.CreateLogOutParkingCommand;
import com.quyvx.main_server.api.application.models.camera.CameraInfo;
import com.quyvx.main_server.api.application.services.camera.CameraService;
import com.quyvx.main_server.api.application.services.log.LogService;
import com.quyvx.main_server.api.dto.log.AuthenticationParkingReq;
import com.quyvx.main_server.api.dto.log.AuthenticationParkingRes;
import com.quyvx.main_server.shared.constants.ProjectConstants;
import com.quyvx.main_server.shared.services.GCSService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@AllArgsConstructor
@RequestMapping("authentication")
@Slf4j
@RestController
public class LogController {
    private final CameraService cameraService;
    private final Pipeline pipeline;
    private final LogService logService;

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

}
