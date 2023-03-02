package bg.softuni.security.service;


import bg.softuni.security.model.UserEntity;
import bg.softuni.security.model.UserRoleEntity;
import bg.softuni.security.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// This is not annotated as @Service, because the return is @Bean
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserDetails userDetails =  this.userRepository.findByEmail(username)
                .map(this::userMapper)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found!"));
       
       return userDetails;
    }

    private UserDetails userMapper(UserEntity userEntity) {
        UserDetails result =
                User.builder()
                        .username(userEntity.getEmail())
                        .password(userEntity.getPassword())
                        .authorities(userEntity.getUserRoles()
                                .stream()
                                .map(this::userRoleMapper)
                                .toList()).build();

        return result;
    }

    private GrantedAuthority userRoleMapper(UserRoleEntity userRole) {
        return new SimpleGrantedAuthority("ROLE_" + userRole.getUserRole().name());
    }
}
