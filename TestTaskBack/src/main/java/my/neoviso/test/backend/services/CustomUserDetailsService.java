package my.neoviso.test.backend.services;

import my.neoviso.test.backend.entities.CustomUserDetails;
import my.neoviso.test.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username : " + username));;
        return CustomUserDetails.build(user);
    }
}
