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

    @Query(value = "SELECT M.ID, M.publisher, M.published_year, M.category, M.book_name, M.author, string_agg(M.header_content, '/ ') as header_content, M.CONTENT, M.page_no " +
            "from " +
            "(select b.id, b.publisher, b.published_year, c.name as category, b.name as book_name, b.author, tc.id as header_id, tc.header_content, " +
            "concat( " +
            "right(split_part(upper(regexp_replace(t.content, '\\s+', ' ', 'g')), upper(?1), generate_series(1, t.times)),30), " +
            "    '<span style=\"background-color: yellow !important;\">', " +
            "    upper(?1), " +
            "    '</span>', " +
            "    left(split_part(upper(regexp_replace(t.content, '\\s+', ' ', 'g')), upper(?1), generate_series(1, t.times)+1),30) " +
            ") as content, " +
            "t.page_no " +
            "from  " +
            "( " +
            " select *, " +
            " ((char_length(regexp_replace(content, '\\s+', ' ', 'g')) - char_length(replace(upper(regexp_replace(content, '\\s+', ' ', 'g')), upper(?1), '')))/ char_length(?1)) as times " +
            " from page " +
            " where upper(regexp_replace(content, '\\s+', ' ', 'g')) like upper(concat('%',?1,'%')) " +
            ") as t, " +
            "book b, category c, table_content tc " +
            "where " +
            "t.book_id = b.id and " +
            "c.id = b.category_id and  " +
            "tc.book_id = b.id and " +
            "tc.from_page <= t.page_no and " +
            "tc.to_page >= t.page_no " +
            "ORDER BY b.ID, T.page_no, CONTENT, header_id) M " +
            "GROUP BY M.ID, M.publisher, M.published_year, M.category, M.book_name, M.author, M.CONTENT, M.page_no "+
            "ORDER BY M.ID, M.page_no ",
            countQuery ="select count(*) from " +
                    "( " +
                    "SELECT M.ID, M.publisher, M.published_year, M.category, M.book_name, M.author, string_agg(M.header_content, '/ '), M.CONTENT, M.page_no " +
                    "from " +
                    "(select b.id, b.publisher, b.published_year, c.name as category, b.name as book_name, b.author, tc.id as header_id, tc.header_content, " +
                    "concat( " +
                    "right(split_part(upper(regexp_replace(t.content, '\\s+', ' ', 'g')), upper(?1), generate_series(1, t.times)),30), " +
                    "    upper(?1), " +
                    "    left(split_part(upper(regexp_replace(t.content, '\\s+', ' ', 'g')), upper(?1), generate_series(1, t.times)+1),30) " +
                    ") as content, " +
                    "t.page_no " +
                    "from  " +
                    "( " +
                    " select *, " +
                    " ((char_length(regexp_replace(content, '\\s+', ' ', 'g')) - char_length(replace(upper(regexp_replace(content, '\\s+', ' ', 'g')), upper(?1), '')))/ char_length(?1)) as times " +
                    " from page " +
                    " where upper(regexp_replace(content, '\\s+', ' ', 'g')) like upper(concat('%',?1,'%')) " +
                    ") as t, " +
                    "book b, category c, table_content tc " +
                    "where " +
                    "t.book_id = b.id and " +
                    "c.id = b.category_id and  " +
                    "tc.book_id = b.id and " +
                    "tc.from_page <= t.page_no and " +
                    "tc.to_page >= t.page_no) M " +
                    "GROUP BY M.ID, M.publisher, M.published_year, M.category, M.book_name, M.author, M.CONTENT, M.page_no " +
                    ")",
            nativeQuery = true)
    org.springframework.data.domain.Page<List<Map<String, Object>>> findByContentContaining(String content, Pageable pageable);

    @Query(value = "SELECT M.ID, M.publisher, M.published_year, M.category, M.book_name, M.author, string_agg(M.header_content, '/ ') as header_content, M.CONTENT, M.page_no " +
            "from " +
            "(select b.id,b.publisher, b.published_year, c.name as category, b.name as book_name, b.author, tc.id as header_id, tc.header_content, " +
            "concat( " +
            "right(split_part(upper(regexp_replace(t.content, '\\s+', ' ', 'g')), upper(?1), generate_series(1, t.times)),30), " +
            "    '<span style=\"background-color: yellow !important;\">', " +
            "    upper(?1), " +
            "    '</span>', " +
            "    left(split_part(upper(regexp_replace(t.content, '\\s+', ' ', 'g')), upper(?1), generate_series(1, t.times)+1),30) " +
            ") as content, " +
            "t.page_no " +
            "from  " +
            "( " +
            " select *, " +
            " ((char_length(regexp_replace(content, '\\s+', ' ', 'g')) - char_length(replace(upper(regexp_replace(content, '\\s+', ' ', 'g')), upper(?1), '')))/ char_length(?1)) as times " +
            " from page " +
            " where upper(regexp_replace(content, '\\s+', ' ', 'g')) like upper(concat('%',?1,'%')) " +
            ") as t, " +
            "book b, category c, table_content tc " +
            "where " +
            "t.book_id = b.id and " +
            "c.id = b.category_id and  " +
            "c.name = ?2  and " +
            "tc.book_id = b.id and " +
            "tc.from_page <= t.page_no and " +
            "tc.to_page >= t.page_no " +
            "ORDER BY b.ID, T.page_no, CONTENT, header_id) M " +
            "GROUP BY M.ID, M.publisher, M.published_year, M.category, M.book_name, M.author, M.CONTENT, M.page_no "+
            "ORDER BY M.ID, M.page_no ",
            countQuery ="select " +
                    "COUNT(*) " +
                    "from " +
                    "( " +
                    "SELECT M.ID, M.publisher, M.published_year, M.category, M.book_name, M.author, string_agg(M.header_content, '/ '), M.CONTENT, M.page_no " +
                    "from " +
                    "(select b.id,b.publisher, b.published_year, c.name as category, b.name as book_name, b.author, tc.id as header_id, tc.header_content, " +
                    "concat( " +
                    "right(split_part(upper(regexp_replace(t.content, '\\s+', ' ', 'g')), upper(?1), generate_series(1, t.times)),30), " +
                    "    upper(?1), " +
                    "    left(split_part(upper(regexp_replace(t.content, '\\s+', ' ', 'g')), upper(?1), generate_series(1, t.times)+1),30) " +
                    ") as content, " +
                    "t.page_no " +
                    "from  " +
                    "( " +
                    " select *, " +
                    " ((char_length(regexp_replace(content, '\\s+', ' ', 'g')) - char_length(replace(upper(regexp_replace(content, '\\s+', ' ', 'g')), upper(?1), '')))/ char_length(?1)) as times " +
                    " from page " +
                    " where upper(regexp_replace(content, '\\s+', ' ', 'g')) like upper(concat('%',?1,'%')) " +
                    ") as t, " +
                    "book b, category c, table_content tc " +
                    "where " +
                    "t.book_id = b.id and " +
                    "c.id = b.category_id and  " +
                    "c.name = ?2  and " +
                    "tc.book_id = b.id and " +
                    "tc.from_page <= t.page_no and " +
                    "tc.to_page >= t.page_no) M " +
                    "GROUP BY M.ID, M.publisher, M.published_year, M.category, M.book_name, M.author, M.CONTENT, M.page_no "+
                    ") ",
            nativeQuery = true)
    org.springframework.data.domain.Page<List<Map<String, Object>>> findByContentAndCategory(String content, String category, Pageable pageable);
}
