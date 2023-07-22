package truclam.library.truc_lam_library.service;

import org.springframework.stereotype.Service;
import truclam.library.truc_lam_library.constant.ResponseObject;

@Service
public interface BookService {
    ResponseObject getBookDetails(Integer id);

    ResponseObject getList(String category, String search, Integer page, Integer size, String order, String column, String isContaining);
}
