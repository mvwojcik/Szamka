package pl.mvwojcik.security.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mvwojcik.security.model.UserAccessDetails;
import pl.mvwojcik.security.model.UserAccessDetailsMapper;
import pl.mvwojcik.user.User;
import pl.mvwojcik.user.UserRegistrationDTO;

@Primary
@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserAccessDetailsRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(UserAccessDetailsMapper::mapUserToSpring)
                .orElseThrow(RuntimeException::new);
    }

    public UserAccessDetails createUserAccessDetails(UserRegistrationDTO userRegistrationDTO) {
        return UserAccessDetails.builder().username(userRegistrationDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(userRegistrationDTO.getPassword()))
                .active(true)
                .locked(false)
                .role("USER")
                .build();
    }

    public UserAccessDetails save(UserRegistrationDTO userRegistrationDTO) {
        return userRepository.save(createUserAccessDetails(userRegistrationDTO));
    }
}
