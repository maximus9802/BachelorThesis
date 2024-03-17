package com.quyvx.main_server.api.application.commands.company.delete_company_command;

import an.awesome.pipelinr.Command;
import com.quyvx.main_server.domain.aggregate_models.company_aggregate.Company;
import com.quyvx.main_server.domain.aggregate_models.identity_aggregate.Identity;
import com.quyvx.main_server.domain.repositories.ICompanyRepository;
import com.quyvx.main_server.domain.repositories.IIdentityRepository;
import com.quyvx.main_server.shared.exceptions.NotFoundException;
import com.quyvx.main_server.shared.libs.shared.helpers.MessageHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class DeleteCompanyCommandHandler implements Command.Handler<DeleteCompanyCommand, Long> {

    private final ICompanyRepository companyRepository;
    private final IIdentityRepository identityRepository;

    @Override
    public Long handle(DeleteCompanyCommand command) {
        Company company = companyRepository.findById(command.getCompanyId())
                .orElseThrow(() -> new NotFoundException(MessageHelper.getMessage("company_not_found")));
        company.deleteCompany();
        Identity identity = identityRepository.findById(company.getIdentityId())
                .orElseThrow(() -> new NotFoundException(MessageHelper.getMessage("identity_not_found")));
        identity.deleteIdentity();
        companyRepository.save(company);
        identityRepository.save(identity);
        log.info("----- DeleteCompanyCommandHandler: Delete company {} successfully", command.getCompanyId());
        return company.getId();
    }
}
