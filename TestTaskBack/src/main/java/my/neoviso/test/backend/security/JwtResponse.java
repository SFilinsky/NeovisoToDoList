package my.neoviso.test.backend.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class JwtResponse {

    @Getter @Setter private String accessToken;
    @Getter @Setter private String tokenType = "Bearer";
    @Getter @Setter private String username;
    @Getter private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
        this.accessToken = accessToken;
        this.username = username;
        this.authorities = authorities;
    }

}
