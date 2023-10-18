package truclam.library.truc_lam_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import truclam.library.truc_lam_library.entity.Page;
import truclam.library.truc_lam_library.entity.TableContent;

@Repository
public interface TableContentRepository extends JpaRepository<TableContent, Integer> {

    @Modifying
    @Query(value = "delete from table_content where book_id = ?1",
            nativeQuery = true)
    void deleteByBook(Integer bId);

    @Modifying
    @Query(value = "delete from table_content where book_id is null",
            nativeQuery = true)
    void deleteNoBook();
}
