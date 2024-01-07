package com.quyvx.main_server.api.application.commands.log.login;

import an.awesome.pipelinr.Command;
import com.quyvx.main_server.api.application.queries.status_parking.IStatusParkingQueriesService;
import com.quyvx.main_server.api.dto.license_plate.ApiLicensePlateResponse;
import com.quyvx.main_server.domain.aggregate_models.authentication_history_aggregate.AuthenticationHistory;
import com.quyvx.main_server.domain.aggregate_models.authentication_log_aggregate.AuthenticationLog;
import com.quyvx.main_server.infrastructure.repositories.AuthenticationHistoryRepository;
import com.quyvx.main_server.infrastructure.repositories.AuthenticationLogRepository;
import com.quyvx.main_server.shared.constants.ProjectConstants;
import com.quyvx.main_server.shared.exceptions.BadRequestException;
import com.quyvx.main_server.shared.utils.TimeUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
public class CreateLogInParkingCommandHandler implements Command.Handler<CreateLogInParkingCommand, Long> {

    private final AuthenticationLogRepository authenticationLogRepository;
    private final IStatusParkingQueriesService statusParkingQueriesService;
    private final AuthenticationHistoryRepository authenticationHistoryRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long handle(CreateLogInParkingCommand command) {
        //send url image to AI service to get license plate
        //call API service detect license plate
        log.info("----- Send image {} to AI service to detect license plate", command.getImageUrl());
        ApiLicensePlateResponse apiResponse = randomLicenseResponse(command.getImageUrl());
        if (ObjectUtils.isEmpty(apiResponse.getLicensePlate())) {
            throw new BadRequestException("Can not detect license plate from image");
        }
        log.info("----- Detect license plate in image {} is {}", command.getImageUrl(), apiResponse);
        //create authentication log
        AuthenticationLog authenticationLog = AuthenticationLog.builder()
                .cameraId(command.getCameraId())
                .evidenceLink(command.getImageUrl())
                .date(TimeUtils.now().toLocalDate())
                .time(TimeUtils.now().toLocalTime())
                .isDeleted(false)
                .build();
        AuthenticationLog savedAuthenticationLog = authenticationLogRepository.save(authenticationLog);
        log.info("----- Create authentication log {} successfully", savedAuthenticationLog.getId());
        //create authentication history
        AuthenticationHistory authenticationHistory = AuthenticationHistory.builder()
                .authenLoginId(savedAuthenticationLog.getId())
                .licensePlateCode(command.getLicensePlate())
                .statusParkingId(statusParkingQueriesService.findStatusParkingIdByType(ProjectConstants.STATUS_PARKING).get())
                .build();
        AuthenticationHistory savedHistory = authenticationHistoryRepository.save(authenticationHistory);
        log.info("----- Create authentication history successfully");
        return savedHistory.getId();
    }

    private ApiLicensePlateResponse randomLicenseResponse(String imageUrl) {
        Random random = new Random();
        int firstDigits = random.nextInt(90) + 11;
        char randomChar = (char) (random.nextInt(26) + 'A');
        int secondDigits = random.nextInt(90000) + 10000;

        String licensePlate = firstDigits + "" + randomChar + "-" + secondDigits;
        return ApiLicensePlateResponse.builder()
                .licensePlate(licensePlate)
                .build();
    }
}
