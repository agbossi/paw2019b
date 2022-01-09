package ar.edu.itba.paw.webapp.auth.login;

import ar.edu.itba.paw.webapp.auth.exceptions.AlreadyLoggedInException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e) {
        if (e instanceof AlreadyLoggedInException) {
            httpServletResponse.setStatus(404);
        }
        if (e instanceof BadCredentialsException) {
            if(httpServletRequest.getHeader(EMAIL) == null || httpServletRequest.getHeader(PASSWORD) == null) {
                httpServletResponse.setStatus(422);
            } else {
                httpServletResponse.setStatus(401);
            }
        } else if (e instanceof DisabledException) {
            httpServletResponse.setStatus(403);
        } else {
            httpServletResponse.setStatus(409);
        }
    }
}
