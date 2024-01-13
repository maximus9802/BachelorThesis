package com.quyvx.main_server.api.application.commands.member.update_member_command;

import an.awesome.pipelinr.Command;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateMemberCommand implements Command<Long> {
    private Long identityId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String postCode;
    private String address;
    private String email;
    private String phoneNumber;
}
