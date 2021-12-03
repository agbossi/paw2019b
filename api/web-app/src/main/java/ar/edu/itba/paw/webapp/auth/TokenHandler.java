package ar.edu.itba.paw.webapp.auth;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class TokenHandler {

    @Autowired
    private PawUserDetailsService userService;

    @Autowired
    private String secret;

    public UserDetails parseUserFromToken(String token) {
        String email = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userService.loadUserByUsername(email);
    }

    public String createTokenForUser(String email) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + TimeUnit.HOURS.toMillis(1l));
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }

    public boolean validateToken(String token) {

        if(!Jwts.parser().setSigningKey(secret).isSigned(token)) {
            return false;
        }

        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("Expired or invalid JWT token");
        }
    }
}
