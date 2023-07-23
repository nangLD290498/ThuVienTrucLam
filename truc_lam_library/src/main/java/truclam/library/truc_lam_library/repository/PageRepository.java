package truclam.library.truc_lam_library.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import truclam.library.truc_lam_library.entity.Page;


@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {
    org.springframework.data.domain.Page<Page> findByContentContaining(String content, Pageable pageable);

    @Query(value = "SELECT p.* FROM page p, book b, category c WHERE  p.book_id = b.id AND c.id = b.category_id AND p.content LIKE %?1% and c.name = ?2",
            countQuery ="SELECT COUNT(*) FROM page p, book b, category c WHERE  p.book_id = b.id AND c.id = b.category_id AND p.content LIKE %?1% and c.name = ?2",
            nativeQuery = true)
    org.springframework.data.domain.Page<Page> findByContentAndCategory(String content, String category, Pageable pageable);
}
