package com.quyvx.main_server.api.application.commands.location.delete_location_command;

import an.awesome.pipelinr.Command;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeleteLocationCommand implements Command<Long> {
    private Long locationId;
}
