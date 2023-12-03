package com.quyvx.main_server.shared.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnerServerErrorException extends RuntimeException {

    public InnerServerErrorException(String message) { super(message);}

    public InnerServerErrorException() { super();}
}
