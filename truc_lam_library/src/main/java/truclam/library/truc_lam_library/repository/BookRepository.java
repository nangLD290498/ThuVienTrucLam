package truclam.library.truc_lam_library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import truclam.library.truc_lam_library.entity.Book;

import java.util.List;
import java.util.Map;

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

    @Modifying
    @Query(value = "DELETE FROM book b WHERE  b.id = ?1",
            nativeQuery = true)
    void deleteBook(Integer id);

    @Query(value = "SELECT count(*) as count, b.category_id as cateId FROM book b WHERE  b.category_id = " +
            "(select a. category_id from book a where a.id = ?1) group by b.category_id",
            nativeQuery = true)
    Map<String, Object> countCateByBookId(Integer id);

    @Query(value = "SELECT b.author, COUNT(b.id) FROM book b GROUP BY b.author ORDER BY b.author",
            countQuery ="SELECT COUNT(*) FROM (SELECT b.author FROM book b GROUP BY b.author)",
            nativeQuery = true)
    Page<List<Map<String, Object>>> getAuthors(Pageable pageable);

    @Query(value = "SELECT b.publisher FROM book b GROUP BY b.publisher ORDER BY b.publisher",
            countQuery ="SELECT COUNT(*) FROM (SELECT b.publisher FROM book b GROUP BY b.publisher)",
            nativeQuery = true)
    Page<List<Map<String, Object>>> getPublishers(Pageable pageable);

    Page<Book> findByAuthor(String author, Pageable pageable);
}
