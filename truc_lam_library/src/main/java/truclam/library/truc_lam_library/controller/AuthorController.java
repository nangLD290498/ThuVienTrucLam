package truclam.library.truc_lam_library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import truclam.library.truc_lam_library.constant.Constant;
import truclam.library.truc_lam_library.constant.ResponseObject;
import truclam.library.truc_lam_library.service.BookService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    BookService bookService;

    @GetMapping()
    public Page<List<Map<String, Object>>> specialSearch(@RequestParam Integer page) {
        return bookService.getAuthors( page, Constant.PAGE_SIZE);
    }


}
