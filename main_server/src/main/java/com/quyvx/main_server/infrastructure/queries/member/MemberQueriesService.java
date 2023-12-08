package com.quyvx.main_server.infrastructure.queries.member;

import com.quyvx.main_server.api.application.models.member.MemberInfo;
import com.quyvx.main_server.api.application.queries.member.IMemberQueriesService;
import com.quyvx.main_server.infrastructure.jpa_repositories.MemberJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberQueriesService implements IMemberQueriesService {
    private final MemberJpaRepository memberJpaRepository;


    @Override
    public Optional<MemberInfo> getMemberInfoByMemberId(Long memberId) {
        return memberJpaRepository.getMemberInfoByMemberId(memberId);
    }
}
