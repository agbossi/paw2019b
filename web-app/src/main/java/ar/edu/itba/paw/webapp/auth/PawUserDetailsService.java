package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class PawUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService us;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        final User user = us.findUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("No user by the email " + email);
        }

        final Collection<? extends GrantedAuthority> authorities;

        if(user.getEmail().equals("admin@test.com")){
            authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else if(us.isDoctor(email)){
            authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_DOCTOR"));
        }else {
            authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new org.springframework.security.core.userdetails.User(email, user.getPassword(), authorities);
    }
}
