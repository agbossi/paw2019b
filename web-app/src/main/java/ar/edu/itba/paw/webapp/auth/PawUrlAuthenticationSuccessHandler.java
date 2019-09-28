package ar.edu.itba.paw.webapp.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class PawUrlAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public PawUrlAuthenticationSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            String redirectUrl = (String) session.getAttribute("url_prior_login");
            if (redirectUrl != null) {

                // we do not forget to clean this attribute from session
                session.removeAttribute("url_prior_login");
                String role = determineTargetUrl(authentication);
                if(role.equals("ROLE_ADMIN") || role.equals("ROLE_DOCTOR")){
                    getRedirectStrategy().sendRedirect(request, response, "/admin");
                }else {
                    // then we redirect
                    getRedirectStrategy().sendRedirect(request, response, getDefaultTargetUrl());
                }
            } else {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
    protected String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;
        boolean isDoctor = false;

        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        label:
        for (GrantedAuthority grantedAuthority : authorities) {
            switch (grantedAuthority.getAuthority()) {
                case "ROLE_ADMIN":
                    isAdmin = true;
                    break label;
                case "ROLE_DOCTOR":
                    isDoctor = true;
                    break label;
                case "ROLE_USER":
                    isUser = true;
                    break label;
            }
        }

        if (isUser) {
            return "ROLE_USER";
        } else if (isAdmin) {
            return "ROLE_ADMIN";
        }  else if(isDoctor){
            return "ROLE_DOCTOR";
        } else {
            throw new IllegalStateException();
        }
    }
}
