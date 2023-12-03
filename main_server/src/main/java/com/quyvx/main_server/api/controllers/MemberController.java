package com.quyvx.main_server.api.controllers;


import com.quyvx.main_server.api.application.models.member.MemberInfo;
import com.quyvx.main_server.api.application.queries.member.IMemberQueriesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/member")
@Slf4j
public class MemberController {
    private final IMemberQueriesService memberQueriesService;

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
}
