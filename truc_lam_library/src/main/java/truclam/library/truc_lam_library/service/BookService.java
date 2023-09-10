package truclam.library.truc_lam_library.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import truclam.library.truc_lam_library.constant.ResponseObject;
import truclam.library.truc_lam_library.entity.Book;

import java.util.List;
import java.util.Map;

@Service
public interface BookService {
    ResponseObject getBookDetails(Integer id);

    ResponseObject getList(String category, String search, Integer page, Integer size, String order, String column, String isContaining);

    Page<List<Map<String, Object>>> specialSearch(String category, String searchText, Integer page, Integer size);

    ResponseObject getBooksWithCategories();

    Book getById(Integer id);

    ResponseObject getBooksByCate(Integer page, Integer cateId, Integer id);

    ResponseObject getBooks(Integer page);
}
