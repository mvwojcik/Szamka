package pl.mvwojcik.security.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class UserAccessDetailsMapper {

    public static UserDetails mapUserToSpring(UserAccessDetails user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getActive(),
                true,
                true,
                !user.getLocked(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
    }

}
