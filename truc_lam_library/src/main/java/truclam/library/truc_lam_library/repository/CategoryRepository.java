package truclam.library.truc_lam_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import truclam.library.truc_lam_library.entity.Category;

import java.util.List;
import java.util.Map;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);

    @Query(value = "SELECT id, name FROM category",
            nativeQuery = true)
    List<Map<String, Object>> findAllForHome();

    @Query(value = "SELECT id, name, (SELECT count(*) FROM book b WHERE b.category_id = c.id) FROM category c",
            nativeQuery = true)
    List<Map<String, Object>> getForListing();
}
