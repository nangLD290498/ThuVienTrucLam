package truclam.library.truc_lam_library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import truclam.library.truc_lam_library.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findByName(String name);

    Page<Book> findByNameLike(String name, Pageable pageable);
    Page<Book> findByAuthorLike(String author, Pageable pageable);
    Page<Book> findByPublishedYearLike(String publishedYear, Pageable pageable);
    Page<Book> findByPublisherLike(String publisher, Pageable pageable);
    Page<Book> findByNameContaining(String name, Pageable pageable);
    Page<Book> findByAuthorContaining(String author, Pageable pageable);
    Page<Book> findByPublishedYearContaining(String publishedYear, Pageable pageable);
    Page<Book> findByPublisherContaining(String publisher, Pageable pageable);

    @Query(value = "SELECT b.* FROM book b WHERE  b.category_id = ?1",
            countQuery ="SELECT COUNT(*) FROM book b WHERE  b.category_id = ?1",
            nativeQuery = true)
    Page<Book> findByCate(Integer cateId, Pageable pageable);

    @Query(value = "SELECT b.* FROM book b WHERE  b.category_id = ?1 limit 4",
            nativeQuery = true)
    List<Book> findFirst4ByCate(Integer cateId);

}
