package ar.edu.itba.paw.webapp.auth.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidRequestEcxeption extends AuthenticationException {
    public InvalidRequestEcxeption(String msg) {
        super(msg);
    }
}
