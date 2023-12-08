package com.quyvx.main_server.api.application.queries.member;

import com.quyvx.main_server.api.application.models.member.MemberInfo;

import java.util.Optional;

public interface IMemberQueriesService {

    Optional<MemberInfo> getMemberInfoByMemberId(Long memberId);
}
