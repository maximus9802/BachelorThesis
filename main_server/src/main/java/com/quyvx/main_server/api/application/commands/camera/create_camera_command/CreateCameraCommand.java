package com.quyvx.main_server.api.application.commands.camera.create_camera_command;

import an.awesome.pipelinr.Command;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCameraCommand implements Command<String> {
    private Long locationId;
    private String name;
    private String typeAuth;
}
