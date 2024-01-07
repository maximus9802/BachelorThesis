package com.quyvx.main_server.api.application.commands.manager.create_manager_command;

import an.awesome.pipelinr.Command;
import com.quyvx.main_server.api.application.services.identity.IdentityService;
import com.quyvx.main_server.api.application.services.manager.ManagerService;
import com.quyvx.main_server.domain.aggregate_models.member_aggregate.Member;
import com.quyvx.main_server.infrastructure.repositories.ManagerRepository;
import com.quyvx.main_server.infrastructure.repositories.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class CreateManagerCommandHandler implements Command.Handler<CreateManagerCommand, Long> {
    private final IdentityService identityService;
    private final MemberRepository memberRepository;
    private final ManagerService managerService;
    private final ManagerRepository managerRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long handle(CreateManagerCommand command) {
        //create identity
        Long identityId = identityService.createManager(command.getLoginId(), command.getPassword());

        //create member contain info of manager
        Member member = Member.builder()
                .identityId(identityId)
                .lastName(command.getLastName())
                .firstName(command.getFirstName())
                .dateOfBirth(command.getDateOfBirth())
                .postCode(command.getPostCode())
                .address(command.getAddress())
                .email(command.getEmail())
                .phoneNumber(command.getPhoneNumber())
                .isDeleted(false)
                .build();
        Member savedMember = memberRepository.save(member);
        log.info("----- Member id {} with login id {} is created", savedMember.id, command.getLoginId());

        //create manager with info location

        return managerService.createManager(savedMember.id, command.getLocationsManage());
    }
}
