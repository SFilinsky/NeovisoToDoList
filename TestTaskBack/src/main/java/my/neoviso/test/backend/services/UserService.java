package my.neoviso.test.backend.services;

import my.neoviso.test.backend.entities.User;
import my.neoviso.test.backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

}
