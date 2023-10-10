package truclam.library.truc_lam_library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import truclam.library.truc_lam_library.constant.Constant;
import truclam.library.truc_lam_library.service.BookService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/publisher")
public class PublishserController {
    @Autowired
    BookService bookService;

    @GetMapping()
    public Page<List<Map<String, Object>>> getPublishers(@RequestParam Integer page) {
        return bookService.getPublishers( page, Constant.PAGE_SIZE);
    }
}
