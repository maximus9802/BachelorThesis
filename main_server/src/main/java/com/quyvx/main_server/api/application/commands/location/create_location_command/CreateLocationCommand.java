package com.quyvx.main_server.api.application.commands.location.create_location_command;

import an.awesome.pipelinr.Command;
import com.quyvx.main_server.domain.aggregate_models.location_aggregate.Location;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateLocationCommand implements Command<Location> {
    private Long companyId;

    private String locationName;

    private String address;

    private String permanentLink;
}
