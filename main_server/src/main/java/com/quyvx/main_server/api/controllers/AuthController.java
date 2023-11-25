package com.quyvx.main_server.api.controllers;

import an.awesome.pipelinr.Pipeline;
import com.quyvx.main_server.api.application.commands.auth.login_command.LoginCommand;
import com.quyvx.main_server.api.application.models.identity.IdentityLogin;
import com.quyvx.main_server.api.application.queries.identity.IdentityQueries;
import com.quyvx.main_server.api.application.services.auth.AuthService;
import com.quyvx.main_server.api.dto.auth.LoginRequestDto;
import com.quyvx.main_server.api.dto.auth.LoginResDto;
import com.quyvx.main_server.shared.exceptions.UnauthorizationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final IdentityQueries identityQueries;
    private final AuthService authService;
    private final Pipeline pipeline;

    @PostMapping("/login")
    public LoginResDto login(@RequestBody LoginRequestDto request) {
        Optional<IdentityLogin> identityLogin = identityQueries.getIdentityByLoginId(request.getLoginId());
        if (!authService.comparePassword(identityLogin.get().getPassword(), request.getPassword())) {
            throw new UnauthorizationException();
        }
        LoginCommand command = LoginCommand.builder()
                .identityLogin(identityLogin.get())
                .build();
        return pipeline.send(command);
    }
}
