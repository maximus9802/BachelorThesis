package com.quyvx.main_server.api.application.commands.log.logout;

import an.awesome.pipelinr.Command;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLogOutParkingCommand implements Command<Long> {
    private Long cameraId;
    private String imageUrl;
}
