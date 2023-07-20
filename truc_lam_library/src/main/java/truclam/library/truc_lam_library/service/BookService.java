package truclam.library.truc_lam_library.service;

import org.springframework.stereotype.Service;
import truclam.library.truc_lam_library.constant.ResponseObject;
import truclam.library.truc_lam_library.entity.Book;

@Service
public interface BookService {
    ResponseObject getBookDetails(Integer id);
}
