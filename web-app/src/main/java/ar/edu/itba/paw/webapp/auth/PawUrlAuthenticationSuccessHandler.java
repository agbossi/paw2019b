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

    private static final String adminRole  = "ROLE_ADMIN";
    private static final String doctorRole = "ROLE_DOCTOR";

    private static final String targetURL   = "URL_PRIOR_LOGIN";
    private static final String defaultURL   = "/";
    private static final String login   = "login";
    private static final String signUp   = "signUp";


    public PawUrlAuthenticationSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session != null) {

            String redirectUrl = (String) session.getAttribute(targetURL);

            if (redirectUrl != null) {
                // we do not forget to clean this attribute from session
                session.removeAttribute(targetURL);

                String role = determineRole(authentication);

                if (role.equals(adminRole) || role.equals(doctorRole)) {
                    getRedirectStrategy().sendRedirect(request, response, defaultURL);
                } else {
                    // we redirect home page
                    if (!redirectUrl.contains(login) && !redirectUrl.contains(signUp)) {
                        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
                    } else {
                        super.onAuthenticationSuccess(request, response, authentication);
                    }
                }
            }
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

    protected String determineRole(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();

        if (!authorities.isEmpty()) {
            for (GrantedAuthority grantedAuthority : authorities) {
                return grantedAuthority.getAuthority();
                // we just need the first. A user does not have +1 roles
                // That was the way we did it before, we keep that logic.
            }
        } else {
            throw new IllegalStateException();
        }
        return null;
    }
}
