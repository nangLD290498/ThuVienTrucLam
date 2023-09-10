package truclam.library.truc_lam_library.service;

import org.springframework.stereotype.Service;
import truclam.library.truc_lam_library.constant.ResponseObject;

import java.util.List;

@Service
public interface CategoryService {
    ResponseObject getCategories();
}
