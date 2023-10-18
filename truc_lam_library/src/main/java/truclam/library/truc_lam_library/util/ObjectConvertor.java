package truclam.library.truc_lam_library.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import truclam.library.truc_lam_library.entity.Book;
import truclam.library.truc_lam_library.entity.TableContent;
import truclam.library.truc_lam_library.service.BookServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

public class ObjectConvertor {
    static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    public static TableContent mapToTableContent(Map<String, Object> mapData){
        if(mapData==null || mapData.size()==0) return null;
        TableContent tableContent = new TableContent();
        tableContent.setHeaderContent(mapData.get("headerContent")!=null?mapData.get("headerContent").toString():null);
        tableContent.setFromPage(mapData.get("fromPage")!=null?Integer.parseInt(mapData.get("fromPage").toString()):null);
        tableContent.setToPage(mapData.get("toPage")!=null?Integer.parseInt(mapData.get("toPage").toString()):null);
        return tableContent;
    }

    public static Map<String, Object> objectToMap(Book book){
        Map<String, Object> result = new HashMap<>();
        if(book.getCategory()!=null){
            book.getCategory().setBooks(null);
        }
        if(book.getTableContents()!=null){
            for (TableContent t: book.getTableContents()) {
                t.setBook(null);
            }
        }
        List<Map<String, Object>> tableContentFormatted = findChildrenHeaders(book.getTableContents(), null);
        tableContentFormatted = tableContentFormatted == null ? null :
                tableContentFormatted.stream().sorted(Comparator.comparing(a -> ((Integer) a.get("id"))))
                .collect(Collectors.toList());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> bookMap = objectMapper.convertValue(book, new TypeReference<Map<String, Object>>() {});
        bookMap.remove("tableContents");
        bookMap.put("tableContent", tableContentFormatted);
        return bookMap;
    }

    public static List<Map<String, Object>> findChildrenHeaders(List<TableContent> headers, TableContent parent){
        if(headers==null||headers.size()==0) return null;
        List<Map<String, Object>> result = new ArrayList<>();
        for (TableContent header: headers) {
            Map<String, Object> headerAfter = new HashMap<>();
            List<Map<String, Object>> childs = new ArrayList<>();
            if(header.getParent() == parent) {
                logger.info("processing header: {}",header.toString());
                headerAfter.put("id", header.getId());
                headerAfter.put("headerContent", header.getHeaderContent());
                headerAfter.put("fromPage", header.getFromPage());
                headerAfter.put("toPage", header.getToPage());
                childs = findChildrenHeaders(headers, header);
                headerAfter.put("childs", childs);
                result.add(headerAfter);
            }
        }
        return result;
    }
}
