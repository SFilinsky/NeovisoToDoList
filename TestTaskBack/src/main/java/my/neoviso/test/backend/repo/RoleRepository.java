package my.neoviso.test.backend.repo;

import my.neoviso.test.backend.entities.Role;
import my.neoviso.test.backend.entities.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}                                       