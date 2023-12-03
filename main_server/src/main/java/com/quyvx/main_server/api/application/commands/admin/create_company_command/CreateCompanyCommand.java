package com.quyvx.main_server.api.application.commands.admin.create_company_command;

import an.awesome.pipelinr.Command;
import com.quyvx.main_server.domain.aggregate_models.company_aggregate.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreateCompanyCommand implements Command<Company> {
    private String loginId;

    private String password;

    private String companyName;

    private String address;

    private String phoneNumber;

}
