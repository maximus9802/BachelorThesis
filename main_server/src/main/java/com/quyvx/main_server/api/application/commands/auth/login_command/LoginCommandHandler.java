package com.quyvx.main_server.api.application.commands.auth.login_command;

import an.awesome.pipelinr.Command;
import com.quyvx.main_server.api.application.queries.auth.AuthQueries;
import com.quyvx.main_server.api.dto.auth.LoginResDto;
import com.quyvx.main_server.shared.exceptions.UnauthorizationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class LoginCommandHandler implements Command.Handler<LoginCommand, LoginResDto> {
    private final AuthQueries authQueries;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LoginResDto handle(LoginCommand command) {

        List<String> roles = authQueries.getIdentitiesRoles(command.getIdentityLogin());
        if (roles.isEmpty()) {
            throw new UnauthorizationException();
        }

        return null;
    }
}
