package ar.edu.itba.paw.webapp.auth;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenAuthenticationService {
    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    @Autowired
    private TokenHandler tokenHandler;

    public void addAuthentication(HttpServletResponse response, Authentication authentication) {
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(authentication.getName()));
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null && tokenHandler.validateToken(token)) {
            final UserDetails user = tokenHandler.parseUserFromToken(token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }

    public String createTokenForUser(String email) {
        return tokenHandler.createTokenForUser(email);
    }

    public Map<String, String> readBodyForm(HttpServletRequest request) throws IOException {
        String body = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
        body = java.net.URLDecoder.decode(body, request.getCharacterEncoding());
        Map<String, String> map = new HashMap<>();

        String[] pairs = body.split("&");

        for(String pair: pairs) {
            String[] split = pair.split("=");
            map.put(split[0], split[1]);
        }

        return map;
    }
}
