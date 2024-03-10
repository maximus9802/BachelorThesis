package com.quyvx.main_server.api.application.commands.company.delete_company_command;

import an.awesome.pipelinr.Command;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DeleteCompanyCommand implements Command<Long> {
    private Long companyId;
}
