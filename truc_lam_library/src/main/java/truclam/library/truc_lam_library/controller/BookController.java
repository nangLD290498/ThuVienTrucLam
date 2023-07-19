package truclam.library.truc_lam_library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import truclam.library.truc_lam_library.constant.ResponseObject;
import truclam.library.truc_lam_library.entity.Book;
import truclam.library.truc_lam_library.service.BookService;

import java.util.Map;

@RestController
public class BookController {
    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    @GetMapping("/book/{id}")
    public ResponseObject savePdfByPage(@PathVariable Integer id) {
        return bookService.getBookDetails(id);
    }

}
