package ar.edu.itba.paw.webapp.config;

import ar.edu.itba.paw.webapp.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

import java.util.Base64;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@PropertySource(value = "classpath:authKey.properties")
@ComponentScan("ar.edu.itba.paw.webapp.auth")
public class WebAuthConfig extends WebSecurityConfigurerAdapter {
    public static final BCryptPasswordEncoder B_CRYPT_PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Autowired
    private PawUserDetailsService userDetailsService;

    /* @Value("${key}")
     private String rememberMeKey; */

    @Value("${secret}")
    private String authTokenSecretKey;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override protected void configure(final HttpSecurity http) throws Exception{
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/doctors/**").permitAll()
                .antMatchers(HttpMethod.GET, "/specialties").permitAll()
                .antMatchers(HttpMethod.GET, "/locations").permitAll()
                .antMatchers(HttpMethod.GET, "/clinics/**").permitAll()
                .antMatchers(HttpMethod.GET, "/prepaids").permitAll()
                .antMatchers(HttpMethod.POST, "/login").anonymous()
                .antMatchers(HttpMethod.POST, "/patients").permitAll()
                .antMatchers(HttpMethod.POST, "/locations").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/locations/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/specialties").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/specialties/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/prepaids").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/prepaids/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/clinics/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/clinics/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/clinics/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/patients/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/patients/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/doctors/*/profileImage").hasAnyRole("ADMIN", "DOCTOR")
                .antMatchers(HttpMethod.POST, "/doctors").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/doctors/**").hasRole("DOCTOR")
                .antMatchers(HttpMethod.PUT, "/doctors/**").hasRole("DOCTOR")
                .antMatchers(HttpMethod.DELETE, "/doctors/**").hasRole("DOCTOR")
                .antMatchers("/appointments").hasAnyRole("USER", "DOCTOR")
                .antMatchers(HttpMethod.GET).authenticated()
                .antMatchers(HttpMethod.POST).authenticated()
                .antMatchers(HttpMethod.DELETE).authenticated()
                .antMatchers(HttpMethod.PUT).authenticated()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin().usernameParameter("email").passwordParameter("password")
                .loginProcessingUrl("/login").successHandler(myAuthenticationSuccessHandler())
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(userDetailsService)
                .exceptionHandling().authenticationEntryPoint(authEntryPoint())
                .and()
                .csrf().disable();
    }

    @Override public void configure(final WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/favicon.ico", "/403");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return B_CRYPT_PASSWORD_ENCODER;
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new PawUrlStatelessAuthenticationSuccessHandler();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public String authTokenSecretKey() {
        return Base64.getEncoder().encodeToString(authTokenSecretKey.getBytes()); //TODO que va aca?
    }

    @Bean
    public GenericFilterBean authFilter() {
        return new StatelessAuthenticationFilter();
    }

    @Bean
    public AuthenticationEntryPoint authEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }
}
