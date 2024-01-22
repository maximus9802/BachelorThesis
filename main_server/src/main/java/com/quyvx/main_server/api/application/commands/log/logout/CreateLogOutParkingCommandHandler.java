package com.quyvx.main_server.api.application.commands.log.logout;

import an.awesome.pipelinr.Command;
import com.quyvx.main_server.api.application.queries.authentication_history.IAuthenticationHistoryQueriesService;
import com.quyvx.main_server.api.application.queries.authentication_type.IAuthenticationTypeQueriesService;
import com.quyvx.main_server.api.application.queries.status_parking.IStatusParkingQueriesService;
import com.quyvx.main_server.api.dto.license_plate.ApiLicensePlateResponse;
import com.quyvx.main_server.domain.aggregate_models.authentication_history_aggregate.AuthenticationHistory;
import com.quyvx.main_server.domain.aggregate_models.authentication_log_aggregate.AuthenticationLog;
import com.quyvx.main_server.infrastructure.repositories.AuthenticationHistoryRepository;
import com.quyvx.main_server.infrastructure.repositories.AuthenticationLogRepository;
import com.quyvx.main_server.shared.constants.ProjectConstants;
import com.quyvx.main_server.shared.utils.TimeUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
public class CreateLogOutParkingCommandHandler implements Command.Handler<CreateLogOutParkingCommand, Long> {

    private final AuthenticationLogRepository authenticationLogRepository;
    private final AuthenticationHistoryRepository authenticationHistoryRepository;
    private final IAuthenticationHistoryQueriesService authenticationHistoryQueriesService;
    private final IStatusParkingQueriesService statusParkingQueriesService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Long handle(CreateLogOutParkingCommand command) {

        //Send image to AI service to detect license plate
        ApiLicensePlateResponse apiResponse = randomLicenseResponse(command.getImageUrl());
        //create authentication log
        AuthenticationLog authenticationLog = AuthenticationLog.builder()
                .cameraId(command.getCameraId())
                .evidenceLink(command.getImageUrl())
                .time(TimeUtils.now().toLocalTime())
                .date(TimeUtils.now().toLocalDate())
                .isDeleted(false)
                .build();
        AuthenticationLog savedAuthenticationLog= authenticationLogRepository.save(authenticationLog);
        log.info("----- Create authentication log {} successfully", savedAuthenticationLog.getId());
        //check if in authentication history have more than 1 or 0 license plate in parking
//        Long authenticationHistoryId = authenticationHistoryQueriesService.getAuthenticationHistoryInParkingByLicensePlate(apiResponse.getLicensePlate());
        Long authenticationHistoryId = authenticationHistoryQueriesService.getAuthenticationHistoryInParkingByLicensePlate(ProjectConstants.LICENSE_PLATE);
        //need check if location of login different logout
        //update authentication history
        AuthenticationHistory authenticationHistory = authenticationHistoryRepository.findById(authenticationHistoryId).get();
        authenticationHistory.setAuthenLogoutId(savedAuthenticationLog.getId());
        authenticationHistory.setStatusParkingId(statusParkingQueriesService.findStatusParkingIdByType(ProjectConstants.STATUS_PARKING_DONE).get());
        authenticationHistory.setDuration(TimeUtils.calculateDurationInMinutes(authenticationHistory.getCreateAt(), TimeUtils.now()));
        authenticationHistoryRepository.save(authenticationHistory);
        log.info("----- Vehicle have license plate {} change status parking to DONE ", ProjectConstants.LICENSE_PLATE);
        //send bill to service payment
        return authenticationHistoryId;
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
