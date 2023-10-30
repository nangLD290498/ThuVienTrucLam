package truclam.library.truc_lam_library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import truclam.library.truc_lam_library.constant.ERole;
import truclam.library.truc_lam_library.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
