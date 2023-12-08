package com.quyvx.main_server.api.application.commands.admin.create_company_command;

import an.awesome.pipelinr.Command;
import com.quyvx.main_server.api.application.services.identity.IdentityService;
import com.quyvx.main_server.domain.aggregate_models.company_aggregate.Company;
import com.quyvx.main_server.infrastructure.repositories.CompanyRepository;
import com.quyvx.main_server.shared.utils.TimeUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Component
public class CreateCompanyCommandHandler implements Command.Handler<CreateCompanyCommand, Company> {

    private final IdentityService identityService;
    private final CompanyRepository companyRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Company handle(CreateCompanyCommand command) {

        Company company = Company.builder()
                .identityId(identityService.createCompany(command.getLoginId(), command.getPassword()))
                .companyName(command.getCompanyName())
                .phoneNumber(command.getPhoneNumber())
                .address(command.getAddress())
                .permanentLink(command.getPermanentLink())
                .isDeleted(false)
                .build();
        Company savedCompany = companyRepository.save(company);
        log.info("----- Company id: {} - loginId: {} is created", savedCompany.id, command.getLoginId());
        return savedCompany;
    }
}
