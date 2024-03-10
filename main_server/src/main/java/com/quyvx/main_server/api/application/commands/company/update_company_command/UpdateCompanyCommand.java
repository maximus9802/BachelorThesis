package com.quyvx.main_server.api.application.commands.company.update_company_command;

import an.awesome.pipelinr.Command;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UpdateCompanyCommand implements Command<Long> {
    private Long companyId;
    private Long identityId;
    private String loginId;
    private String password;
    private String companyName;
    private String address;
    private String phoneNumber;
    private String email;
    private String permanentLink;
}
