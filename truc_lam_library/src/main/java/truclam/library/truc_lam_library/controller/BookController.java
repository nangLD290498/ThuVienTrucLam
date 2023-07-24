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

    @GetMapping("/books/{id}")
    public ResponseObject getBookByid(@PathVariable Integer id) {
        return bookService.getBookDetails(id);
    }

    @GetMapping("/book/getList/{page}/{size}/{order}/{column}")
    public ResponseObject getBookList(@RequestParam(required = false) String category,
                                        @RequestParam(required = false) String search,
                                        @RequestParam(required = false) String isContaining,
                                        @PathVariable Integer page,
                                        @PathVariable Integer size,
                                        @PathVariable String order,
                                        @PathVariable String column) {
        return bookService.getList(category, search, page, size, order, column, isContaining);
    }

    @GetMapping("/book/specialSearch/{page}/{size}")
    public ResponseObject specialSearch(@RequestParam String searchText,
                                        @PathVariable Integer page,
                                        @RequestParam String category,
                                        @PathVariable Integer size) {
        return bookService.specialSearch(category ,searchText, page, size);
    }

    @GetMapping("/books")
    public ResponseObject getBooks(@RequestParam Integer page) {
        return bookService.getBooks(page);
    }

}
