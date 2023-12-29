package com.quyvx.main_server.api.controllers;


import com.quyvx.main_server.api.application.models.member.MemberInfo;
import com.quyvx.main_server.api.application.queries.member.IMemberQueriesService;
import com.quyvx.main_server.shared.exceptions.ForbiddenException;
import com.quyvx.main_server.shared.libs.application.dto.UserDetail;
import com.quyvx.main_server.shared.libs.application.security.SecurityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/member")
@Slf4j
public class MemberController {
    private final IMemberQueriesService memberQueriesService;
    private final SecurityService securityService;

    @GetMapping("/info/{memberId}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public MemberInfo getMemberInfoByMemberId(@PathVariable("memberId") Long memberId) {
        Optional<MemberInfo> memberInfo = memberQueriesService.getMemberInfoByMemberId(memberId);
        if (memberInfo.isPresent()) {
            return memberInfo.get();
        }
        else {
            return null;
        }
    }

    @PutMapping("/{identityId}")
    @PreAuthorize("hasAnyAuthority('USER', 'MANAGER')")
    public void updateMemberInfo(@PathVariable("identityId") Long identityId) {
        UserDetail userDetail = securityService.getUserDetail();
        if (!Objects.equals(userDetail.getId(), identityId)) {
            throw new ForbiddenException("no_permission");
        }

    }
}
