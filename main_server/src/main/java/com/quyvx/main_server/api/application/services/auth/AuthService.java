package com.quyvx.main_server.api.application.services.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    public boolean comparePassword(String password, String input) {
        return password.equals(input);
    }
}
