package com.quyvx.main_server.api.application.services.identity;

import com.quyvx.main_server.api.application.queries.role.IRoleQueriesService;
import com.quyvx.main_server.domain.aggregate_models.identity_aggregate.Identity;
import com.quyvx.main_server.domain.aggregate_models.identity_aggregate.IdentityRole;
import com.quyvx.main_server.domain.repositories.IIdentityRepository;
import com.quyvx.main_server.shared.constants.RoleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IdentityService {

    private final IIdentityRepository iIdentityRepository;
    private final IRoleQueriesService roleQueriesService;

    public Long createCompany(String loginId, String password){

        IdentityRole role = IdentityRole.builder()
                .roleId(roleQueriesService.getRoleIdByRoleType(RoleEnum.COMPANY.getValue()))
                .build();

        Identity identity = Identity.builder()
                .loginId(loginId)
                .password(password)
                .identityRoles(List.of(role))
                .isDeleted(false)
                .build();

        Identity savedIdentity = iIdentityRepository.save(identity);
        log.info("----- Identity id: {} - loginId: {} is created", savedIdentity.id, savedIdentity.getLoginId());
        return savedIdentity.getId();
    }

    public Long createManager(String loginId, String password) {
        IdentityRole role = IdentityRole.builder()
                .roleId(roleQueriesService.getRoleIdByRoleType(RoleEnum.MANAGER.getValue()))
                .build();

        Identity identity = Identity.builder()
                .loginId(loginId)
                .password(password)
                .identityRoles(List.of(role))
                .isDeleted(false)
                .build();

        Identity savedIdentity = iIdentityRepository.save(identity);
        log.info("----- Identity id: {} - loginId: {} is created", savedIdentity.id, savedIdentity.getLoginId());
        return savedIdentity.getId();
    }

}
