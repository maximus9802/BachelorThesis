package com.quyvx.main_server.api.application.queries.auth;

import com.quyvx.main_server.api.application.models.auth.MemberRole;

import java.util.List;

public interface IAuthQueriesService {

    List<MemberRole> getMemberRoleByMemberId(Long memberId);
}
