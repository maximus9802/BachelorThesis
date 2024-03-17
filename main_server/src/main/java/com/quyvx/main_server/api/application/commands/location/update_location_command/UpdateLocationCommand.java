package com.quyvx.main_server.api.application.commands.location.update_location_command;

import an.awesome.pipelinr.Command;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UpdateLocationCommand implements Command<Long> {
    private Long locationId;
    private Long companyId;
    private String name;
    private String address;
    private String permanentLink;
}
