package com.quyvx.main_server.api.application.commands.location.create_location_command;

import an.awesome.pipelinr.Command;
import com.quyvx.main_server.domain.aggregate_models.location_aggregate.Location;
import com.quyvx.main_server.infrastructure.repositories.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class CreateLocationCommandHandler implements Command.Handler<CreateLocationCommand, Location> {
    private final LocationRepository locationRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Location handle(CreateLocationCommand command) {
        Location location = Location.builder()
                .companyId(command.getCompanyId())
                .name(command.getLocationName())
                .address(command.getAddress())
                .permanentLink(command.getPermanentLink())
                .isDeleted(false)
                .build();
        Location savedLocation = locationRepository.save(location);
        log.info("----- Location id: {} - permanent link: {} is created", savedLocation.id, command.getPermanentLink());
        return savedLocation;
    }
}
