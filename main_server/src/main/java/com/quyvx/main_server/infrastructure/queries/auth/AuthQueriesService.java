package com.quyvx.main_server.infrastructure.queries.auth;

import com.quyvx.main_server.api.application.models.auth.MemberRole;
import com.quyvx.main_server.api.application.queries.auth.IAuthQueriesService;
import com.quyvx.main_server.infrastructure.jpa_repositories.MemberJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AuthQueriesService implements IAuthQueriesService {
    private final MemberJpaRepository memberJpaRepository;
    @Override
    public List<MemberRole> getMemberRoleByMemberId(Long memberId) {
        return memberJpaRepository.findMemberRolesByMemberId(memberId);
    }
}
