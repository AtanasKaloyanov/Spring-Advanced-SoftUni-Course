package bg.softuni.security.config;

import bg.softuni.security.model.enums.UserRoleEnum;
import bg.softuni.security.repository.UserRepository;
import bg.softuni.security.service.AppUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    // Exposition of 3 things:
    // 1. PasswordEncoder
    // 2. SecurityFilterChain
    // 3. UserDetailsService

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // define which request are allowed and which are not
                .authorizeHttpRequests()
                // everyone can download static resources (css, javascript, images)
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                // evryone can login and register
                .antMatchers("/", "/users/login", "/users/register").permitAll()
                // pages available only for moderators
                .antMatchers("/pages/moderators").hasRole(UserRoleEnum.MODERATOR.name())
                // pages available only for admins
                .antMatchers("/pages/admins").hasRole(UserRoleEnum.ADMIN.name())
                // al other pages are avilable for logged users
                .anyRequest().authenticated()
                .and()
                // configuration of form login
                .formLogin()
                // the custom login form
                .loginPage("/users/login")
                // the name of the username form field
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                // the name of the username form field
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                // where to go in case that the login in succesful
                .defaultSuccessUrl("/")
                // where to go in case that the login failed
                .failureForwardUrl("/users/login-error")
                .and()
                // configure logout
                .logout()
                // which is the logout url
                .logoutUrl("/users/logout")
                // invalidate the session and delete the cookies
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);
    }
}
