package com.quyvx.main_server.api.application.commands.log.login;

import an.awesome.pipelinr.Command;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLogInParkingCommand implements Command<Long> {
    private Long cameraId;
    private String imageUrl;
    private String licensePlate;
}
