package com.quyvx.main_server.api.application.commands.company.update_company_command;

import an.awesome.pipelinr.Command;
import com.quyvx.main_server.domain.aggregate_models.company_aggregate.Company;
import com.quyvx.main_server.domain.aggregate_models.identity_aggregate.Identity;
import com.quyvx.main_server.domain.repositories.ICompanyRepository;
import com.quyvx.main_server.domain.repositories.IIdentityRepository;
import com.quyvx.main_server.shared.exceptions.NotFoundException;
import com.quyvx.main_server.shared.libs.shared.helpers.MessageHelper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class UpdateCompanyCommandHandler implements Command.Handler<UpdateCompanyCommand, Long> {
    private final IIdentityRepository identityRepository;
    private final ICompanyRepository companyRepository;


    @Override
    @Transactional(rollbackOn = Exception.class)
    public Long handle(UpdateCompanyCommand command) {
        //update entity
        Identity identity = identityRepository.findById(command.getIdentityId())
                .orElseThrow(() -> new NotFoundException(MessageHelper.getMessage("identity_not_found")));
        if (!StringUtils.equals(identity.getLoginId(), command.getLoginId())
                || !StringUtils.equals(identity.getPassword(), command.getPassword())) {
            identity.updateIdentity(command.getLoginId(), command.getPassword());
            identityRepository.save(identity);
            log.info("----- UpdateCompanyCommandHandler: Update identity {} successfully", identity.id);
        }
        //update company
        Company company = companyRepository.findById(command.getCompanyId())
                .orElseThrow(() -> new NotFoundException(MessageHelper.getMessage("company_not_found")));
        company.updateCompanyInfo(command.getCompanyName(), command.getPhoneNumber(), command.getAddress(),
                command.getEmail(), command.getPermanentLink());
        companyRepository.save(company);
        log.info("----- UpdateCompanyCommandHandler: Update company {} successfully", company.id);
        return null;
    }
}
