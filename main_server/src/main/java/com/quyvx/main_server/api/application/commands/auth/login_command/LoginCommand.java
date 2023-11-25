package com.quyvx.main_server.api.application.commands.auth.login_command;

import an.awesome.pipelinr.Command;
import com.quyvx.main_server.api.application.models.identity.IdentityLogin;
import com.quyvx.main_server.api.dto.auth.LoginResDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class LoginCommand implements Command<LoginResDto> {
    private IdentityLogin identityLogin;
}
