package com.quyvx.main_server.api.application.queries.auth;

import com.quyvx.main_server.api.application.models.auth.MemberRole;
import com.quyvx.main_server.api.application.models.identity.IdentityLogin;
import com.quyvx.main_server.shared.constants.RoleEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AuthQueries {
    private final IAuthQueriesService authQueriesService;

    public List<String> getCompanyRoles() {
        return List.of(RoleEnum.COMPANY.toString());
    }

    public List<String> getMemberRoles(Long memberId) {
        return authQueriesService.getMemberRoleByMemberId(memberId)
                .stream().map(MemberRole::getType).toList();
    }

    public List<String> getIdentitiesRoles(IdentityLogin identityLogin) {
        List<String> roles = new ArrayList<>();

        if (identityLogin.getCompanyId() != null) {
            roles.addAll(getCompanyRoles());
        }
        if (identityLogin.getMemberId() != null) {
            roles.addAll(getMemberRoles(identityLogin.getMemberId()));
        }
        return roles;
    }

}
