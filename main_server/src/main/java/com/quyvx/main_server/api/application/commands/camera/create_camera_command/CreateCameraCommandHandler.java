package com.quyvx.main_server.api.application.commands.camera.create_camera_command;

import an.awesome.pipelinr.Command;
import com.quyvx.main_server.api.application.queries.authentication_type.IAuthenticationTypeQueriesService;
import com.quyvx.main_server.domain.aggregate_models.camera_aggregate.Camera;
import com.quyvx.main_server.domain.aggregate_models.location_aggregate.Location;
import com.quyvx.main_server.infrastructure.repositories.CameraRepository;
import com.quyvx.main_server.infrastructure.repositories.LocationRepository;
import com.quyvx.main_server.shared.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class CreateCameraCommandHandler implements Command.Handler<CreateCameraCommand, String> {
    private final IAuthenticationTypeQueriesService authenticationTypeQueriesService;
    private final CameraRepository cameraRepository;
    private final LocationRepository locationRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String handle(CreateCameraCommand command) {
        Optional<Location> location = locationRepository.findById(command.getLocationId());
        if (location.isEmpty()) {
            throw new NotFoundException("no_location");
        }
        Camera camera = Camera.builder()
                .name(command.getName())
                .locationId(location.get().id)
                .typeAuthenId(authenticationTypeQueriesService.getAuthenticationTypeIdByTypeName(command.getTypeAuth()))
                .cameraUUID(UUID.randomUUID().toString())
                .isDeleted(false)
                .build();
        Camera savedCamera =  cameraRepository.save(camera);
        log.info("----- Camera id {} with name {} is created", savedCamera.id, command.getName());
        return savedCamera.getCameraUUID();
    }
}
