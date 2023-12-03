package com.quyvx.main_server.shared.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) { super(message);}

    public ForbiddenException() {super();}
}
