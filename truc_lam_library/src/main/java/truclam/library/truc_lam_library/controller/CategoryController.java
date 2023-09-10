package truclam.library.truc_lam_library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import truclam.library.truc_lam_library.constant.Constant;
import truclam.library.truc_lam_library.constant.ResponseObject;
import truclam.library.truc_lam_library.service.CategoryService;

import javax.persistence.Access;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public ResponseObject getAll() {
        return categoryService.getCategories();
    }
}
