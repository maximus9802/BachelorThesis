package com.quyvx.main_server.api.application.commands.location.update_location_command;

import an.awesome.pipelinr.Command;
import com.quyvx.main_server.domain.aggregate_models.location_aggregate.Location;
import com.quyvx.main_server.domain.repositories.ILocationRepository;
import com.quyvx.main_server.shared.exceptions.NotFoundException;
import com.quyvx.main_server.shared.libs.shared.helpers.MessageHelper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class UpdateLocationCommandHandler implements Command.Handler<UpdateLocationCommand, Long> {
    private final ILocationRepository locationRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Long handle(UpdateLocationCommand command) {
        Location location = locationRepository.findById(command.getLocationId()).orElseThrow(
                () -> new NotFoundException(MessageHelper.getMessage("location_not_found")));
        location.updateLocation(command.getAddress(), command.getName());
        location.updatePermanentLinkLocation(command.getPermanentLink());

        locationRepository.save(location);
        return location.getId();
    }
}
