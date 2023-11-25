package com.quyvx.main_server.shared.exceptions;

public class UnauthorizationException extends RuntimeException {
    public UnauthorizationException() {
        super();
    }

    public UnauthorizationException(String message) {
        super(message);
    }
}
