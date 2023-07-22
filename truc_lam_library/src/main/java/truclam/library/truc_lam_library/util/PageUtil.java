package truclam.library.truc_lam_library.util;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class PageUtil {
    public static Map<String, Object> convertToPageObject(Page pageObject, Object result){
        Map<String, Object> pageResult = new HashMap<>();
        pageResult.put("totalElements", pageObject.getTotalElements());
        pageResult.put("totalPages", pageObject.getTotalPages());
        pageResult.put("pageSize", pageObject.getSize());
        pageResult.put("page", pageObject.getPageable().getPageNumber()+1);
        pageResult.put("isFirst", pageObject.isFirst());
        pageResult.put("isLast", pageObject.isLast());
        pageResult.put("numberOfElements", pageObject.getNumberOfElements());
        pageResult.put("elements", result);
        return pageResult;
    }
}
