package com.quyvx.main_server.api.application.services.company;

import com.quyvx.main_server.domain.aggregate_models.company_aggregate.Company;
import com.quyvx.main_server.infrastructure.repositories.CompanyRepository;
import com.quyvx.main_server.shared.constants.RoleEnum;
import com.quyvx.main_server.shared.exceptions.NotFoundException;
import com.quyvx.main_server.shared.libs.application.dto.UserDetail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Boolean canAccessCompanyResource(UserDetail userDetail, Long companyId){
        //role ADMIN_ADMIN and COMPANY can access
        if (userDetail.getRoles().contains(RoleEnum.ADMIN_ADMIN.getValue())) { //identity is ADMIN_ADMIN
            return true;
        }
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isEmpty()) {
            throw new NotFoundException("not_found_company");
        }
        return Objects.equals(company.get().getIdentityId(), userDetail.getId());
    }
}
