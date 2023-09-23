package truclam.library.truc_lam_library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import truclam.library.truc_lam_library.constant.ResponseObject;
import truclam.library.truc_lam_library.constant.StatusEnum;
import truclam.library.truc_lam_library.constant.SuccessMessage;
import truclam.library.truc_lam_library.entity.Category;
import truclam.library.truc_lam_library.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public ResponseObject getCategories() {
        ResponseObject result = new ResponseObject();
        List<Map<String, Object>> categories = categoryRepository.getForListing();
        if(categories.size()>0) {
            result.setStatus(StatusEnum.OK.toString());
            result.setMessage(SuccessMessage.BOOK_FOUND);
            result.setContent(categories);
            return result;
        }
        result.setStatus(StatusEnum.OK.toString());
        result.setMessage(SuccessMessage.BOOK_FOUND);
        result.setContent(new ArrayList<>());
        return result;
    }

}
