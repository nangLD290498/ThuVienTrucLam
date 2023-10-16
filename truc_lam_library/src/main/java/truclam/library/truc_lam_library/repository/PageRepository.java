package truclam.library.truc_lam_library.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import truclam.library.truc_lam_library.entity.Page;

import java.util.List;
import java.util.Map;


@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {

    @Modifying
    @Query(value = "delete from page where book_id = ?1",
            nativeQuery = true)
    void deleteByBook(Integer bId);

    @Query(value = "select count(*) from page where book_id = ?1",
            nativeQuery = true)
    Integer countByBookId(Integer bookId);

    @Query(value = "select " +
            "b.id, b.publisher, b.published_year, c.name as category, b.name as book_name, b.author, tc.id as header_id, tc.header_content, " +
            "concat( " +
            "right(split_part(upper(t.content), upper(?1), generate_series(1, t.times)),30), " +
            "    upper(?1), " +
            "    left(split_part(upper(t.content), upper(?1), generate_series(1, t.times)+1),30) " +
            ") as content, " +
            "t.page_no " +
            "from  " +
            "( " +
            " select *, " +
            " ((char_length(content) - char_length(replace(upper(content), upper(?1), '')))/ char_length(?1)) as times " +
            " from page " +
            " where upper(content) like upper(concat('%',?1,'%')) " +
            ") as t, " +
            "book b, category c, table_content tc " +
            "where " +
            "t.book_id = b.id and " +
            "c.id = b.category_id and  " +
            "tc.book_id = b.id and " +
            "tc.from_page <= t.page_no and " +
            "tc.to_page >= t.page_no",
            countQuery ="select " +
                    "COUNT(*) " +
                    "from " +
                    "( " +
                    " select *, " +
                    " generate_series(1, ((char_length(content) - char_length(replace(upper(content), upper(?1), '')))/ char_length(?1))) as index " +
                    " from page " +
                    " where upper(content) like upper(concat('%',?1,'%')) " +
                    ") as t, " +
                    "book b, category c, table_content tc " +
                    "where " +
                    "t.book_id = b.id and " +
                    "c.id = b.category_id and  " +
                    "tc.book_id = b.id and " +
                    "tc.from_page <= t.page_no and " +
                    "tc.to_page >= t.page_no",
            nativeQuery = true)
    org.springframework.data.domain.Page<List<Map<String, Object>>> findByContentContaining(String content, Pageable pageable);

    @Query(value = "select " +
            "b.id,b.publisher, b.published_year, c.name as category, b.name as book_name, b.author, tc.id as header_id, tc.header_content, " +
            "concat( " +
            "right(split_part(upper(t.content), upper(?1), generate_series(1, t.times)),30), " +
            "    upper(?1), " +
            "    left(split_part(upper(t.content), upper(?1), generate_series(1, t.times)+1),30) " +
            ") as content, " +
            "t.page_no " +
            "from  " +
            "( " +
            " select *, " +
            " ((char_length(content) - char_length(replace(upper(content), upper(?1), '')))/ char_length(?1)) as times " +
            " from page " +
            " where upper(content) like upper(concat('%',?1,'%')) " +
            ") as t, " +
            "book b, category c, table_content tc " +
            "where " +
            "t.book_id = b.id and " +
            "c.id = b.category_id and  " +
            "c.name = ?2  and " +
            "tc.book_id = b.id and " +
            "tc.from_page <= t.page_no and " +
            "tc.to_page >= t.page_no",
            countQuery ="select " +
                    "COUNT(*) " +
                    "from " +
                    "( " +
                    " select *, " +
                    " generate_series(1, ((char_length(content) - char_length(replace(upper(content), upper(?1), '')))/ char_length(?1))) as index " +
                    " from page " +
                    " where upper(content) like upper(concat('%',?1,'%')) " +
                    ") as t, " +
                    "book b, category c, table_content tc " +
                    "where " +
                    "t.book_id = b.id and " +
                    "c.id = b.category_id and  " +
                    "c.name = ?2  and " +
                    "tc.book_id = b.id and " +
                    "tc.from_page <= t.page_no and " +
                    "tc.to_page >= t.page_no",
            nativeQuery = true)
    org.springframework.data.domain.Page<List<Map<String, Object>>> findByContentAndCategory(String content, String category, Pageable pageable);
}
