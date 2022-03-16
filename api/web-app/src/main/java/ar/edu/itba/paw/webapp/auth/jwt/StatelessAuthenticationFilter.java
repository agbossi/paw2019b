package ar.edu.itba.paw.webapp.auth.jwt;

import ar.edu.itba.paw.webapp.auth.TokenAuthenticationService;
import ar.edu.itba.paw.webapp.auth.exceptions.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class StatelessAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private TokenAuthenticationService authenticationService;

    public StatelessAuthenticationFilter() {
        super("/**");
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) res;
        if (!(httpRequest.getMethod().equalsIgnoreCase("OPTIONS"))) {
            try {
                Authentication authentication = authenticationService.getAuthentication(httpRequest);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(req, res);
                SecurityContextHolder.getContext().setAuthentication(null);
            } catch(InvalidTokenException e) {
                ((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            }
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
        }

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        Authentication authentication = authenticationService.getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

}
