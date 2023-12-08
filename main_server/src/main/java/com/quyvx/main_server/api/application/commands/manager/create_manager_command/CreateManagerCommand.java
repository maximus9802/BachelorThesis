package com.quyvx.main_server.api.application.commands.manager.create_manager_command;

import an.awesome.pipelinr.Command;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class CreateManagerCommand implements Command<Long> {
    private String loginId;
    private String password;
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private String postCode;
    private String address;
    private String email;
    private String phoneNumber;
    private List<Long> locationsManage;
}
