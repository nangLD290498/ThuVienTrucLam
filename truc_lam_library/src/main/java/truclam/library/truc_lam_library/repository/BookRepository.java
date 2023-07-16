package truclam.library.truc_lam_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import truclam.library.truc_lam_library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findByName(String name);

}
