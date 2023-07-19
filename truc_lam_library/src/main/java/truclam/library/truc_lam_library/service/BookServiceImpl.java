package truclam.library.truc_lam_library.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import truclam.library.truc_lam_library.constant.ErrorMessage;
import truclam.library.truc_lam_library.constant.ResponseObject;
import truclam.library.truc_lam_library.constant.StatusEnum;
import truclam.library.truc_lam_library.constant.SuccessMessage;
import truclam.library.truc_lam_library.controller.BookController;
import truclam.library.truc_lam_library.entity.Book;
import truclam.library.truc_lam_library.entity.TableContent;
import truclam.library.truc_lam_library.repository.BookRepository;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    BookRepository bookRepository;
    @Override
    public ResponseObject getBookDetails(Integer id) {
        ResponseObject responseObject = new ResponseObject();
        Optional<Book> b = bookRepository.findById(id);
        if(b.isPresent()){
            responseObject.setStatus(StatusEnum.OK.toString());
            responseObject.setMessage(SuccessMessage.BOOK_FOUND);
            if(b.get().getCategory()!=null){
                b.get().getCategory().setBooks(null);
            }
            if(b.get().getTableContents()!=null){
                for (TableContent t: b.get().getTableContents()) {
                    t.setBook(null);
                }
            }
            responseObject.setContent(convertToMap(b.get()));
            logger.info("book found : {}",  b.get());
        }else{
            responseObject.setStatus(StatusEnum.NOK.toString());
            responseObject.setMessage(ErrorMessage.BOOK_NOT_FOUND);
            logger.info("no book found");
        }
        return responseObject;
    }

    Map<String, Object> convertToMap(Book book){
        Map<String, Object> result = new HashMap<>();

        List<Map<String, Object>> tableContentFormatted = findChildrenHeaders(book.getTableContents(), null);
        if(book.getTableContents()!=null && book.getTableContents().size()>0) {
            book.setTableContents(null);
        }
        result.put("tableContent", tableContentFormatted);
        result.put("bookDetails", book);
        return result;
    }

    List<Map<String, Object>> findChildrenHeaders(List<TableContent> headers, TableContent parent){
        if(headers==null||headers.size()==0) return null;
        List<Map<String, Object>> result = new ArrayList<>();
        for (TableContent header: headers) {
            Map<String, Object> headerAfter = new HashMap<>();
            List<Map<String, Object>> childs = new ArrayList<>();
            //if((header.getParent()==null && parent == null) || header.getParent().equals(parent)) {
            if(header.getParent() == parent) {
                logger.info("processing header: {}",header.toString());
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
