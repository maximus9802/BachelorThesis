package com.quyvx.main_server.shared.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() { super(); }

    public NotFoundException(String message) {
        super(message);
    }
}
