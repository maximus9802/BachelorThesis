package com.quyvx.main_server.infrastructure.queries.role;

import com.quyvx.main_server.api.application.queries.role.IRoleQueriesService;
import com.quyvx.main_server.infrastructure.jpa_repositories.RoleJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleQueriesService implements IRoleQueriesService {
    private final RoleJpaRepository roleJpaRepository;

    @Override
    public Long getRoleIdByRoleType(String roleType) {
        return roleJpaRepository.getRoleIdByRoleType(roleType);
    }
}
