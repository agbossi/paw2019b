package ar.edu.itba.paw.webapp.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class SignUpAuthentication {

    @Autowired
    protected AuthenticationManager authenticationManager;

    public String signUpSuccess(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            String redirectUrl = (String) session.getAttribute("url_prior_login");
            if (redirectUrl != null) {
                session.removeAttribute("url_prior_login");
                if(!redirectUrl.contains("login") && !redirectUrl.contains("signUp")){
                    return redirectUrl;
                }
            }
        }
        return "/";
    }
    public void authWithAuthManager(HttpServletRequest request, String email, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);

        // generate session if one doesn't exist
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        authToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
