package bg.softuni.security.service;

import bg.softuni.security.model.UserEntity;
import bg.softuni.security.model.UserRoleEntity;
import bg.softuni.security.model.dto.UserRegisterDTO;
import bg.softuni.security.model.enums.UserRoleEnum;
import bg.softuni.security.repository.UserRepository;
import bg.softuni.security.repository.UserRoleRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService appUserDetailsService;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, UserDetailsService appUserDetailsService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.appUserDetailsService = appUserDetailsService;
    }


    public void init() {
        if (userRepository.count() == 0 && userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setUserRole(UserRoleEnum.ADMIN);

            UserRoleEntity moderatorRole = new UserRoleEntity();
            moderatorRole.setUserRole(UserRoleEnum.MODERATOR);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(moderatorRole);

            initAdmin(List.of(adminRole, moderatorRole));
            initModerator(List.of(moderatorRole));
            initUser(List.of());
        }
    }

    private void initAdmin(List<UserRoleEntity> roles) {
        UserEntity admin = new UserEntity();
        admin.setUserRoles(roles);
        admin.setFirstName("Admin");
        admin.setLastName("Adminov");
        admin.setEmail("admin@example.com");
        admin.setPassword(passwordEncoder.encode("topsecret"));

        userRepository.save(admin);
    }

    private void initModerator(List<UserRoleEntity> roles) {
        UserEntity moderator = new UserEntity();
        moderator.setUserRoles(roles);
        moderator.setFirstName("Moderator");
        moderator.setLastName("Moderatorov");
        moderator.setEmail("moderator@example.com");
        moderator.setPassword(passwordEncoder.encode("topsecret"));

        userRepository.save(moderator);
    }

    private void initUser(List<UserRoleEntity> roles) {
        UserEntity user = new UserEntity();
        user.setUserRoles(roles);
        user.setFirstName("User");
        user.setLastName("Userov");
        user.setEmail("user@example.com");
        user.setPassword(passwordEncoder.encode("topsecret"));

        userRepository.save(user);
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {
          UserEntity newUser = new UserEntity();
          newUser.setEmail(userRegisterDTO.getEmail());
          newUser.setFirstName(userRegisterDTO.getFirstName());
          newUser.setLastName(userRegisterDTO.getLastName());
          newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

          userRepository.save(newUser);

        UserDetails userDetails =  this.appUserDetailsService.loadUserByUsername(newUser.getEmail());

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext()
                .setAuthentication(auth);

    }
}
