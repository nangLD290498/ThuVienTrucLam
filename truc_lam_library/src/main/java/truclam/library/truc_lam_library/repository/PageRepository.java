package truclam.library.truc_lam_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import truclam.library.truc_lam_library.entity.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {
}
