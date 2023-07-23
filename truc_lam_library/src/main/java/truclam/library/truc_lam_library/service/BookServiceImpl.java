package truclam.library.truc_lam_library.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import truclam.library.truc_lam_library.constant.ErrorMessage;
import truclam.library.truc_lam_library.constant.ResponseObject;
import truclam.library.truc_lam_library.constant.StatusEnum;
import truclam.library.truc_lam_library.constant.SuccessMessage;
import truclam.library.truc_lam_library.entity.Book;
import truclam.library.truc_lam_library.entity.TableContent;
import truclam.library.truc_lam_library.repository.BookRepository;
import truclam.library.truc_lam_library.repository.PageRepository;
import truclam.library.truc_lam_library.util.PageUtil;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PageRepository pageRepository;
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

    @Override
    public ResponseObject getList(String category, String search, Integer page, Integer size, String order, String column, String isContaining) {
        ResponseObject responseObject = new ResponseObject();
        Pageable pageable = null;
        if(page==-1 && size ==-1){
            page = 1;
            size = Integer.MAX_VALUE;
        }
        if("ASC".equals(order)){
            pageable = PageRequest.of(page-1, size, Sort.by(column).ascending());
        }else{
            pageable = PageRequest.of(page-1, size, Sort.by(column).descending());
        }
        Page<Book> pageObject = null;

        if(isContaining!=null && isContaining.equals("Y")){
            if (category == null || "*".equals(category)) {
                pageObject = bookRepository.findAll(pageable);
            }
            if ("name".equals(category)) {
                pageObject = bookRepository.findByNameContaining(search, pageable);
            }
            if ("author".equals(category)) {
                pageObject = bookRepository.findByAuthorContaining(search, pageable);
            }
            if ("publishedYear".equals(category)) {
                pageObject = bookRepository.findByPublishedYearContaining(search, pageable);
            }
            if ("publisher".equals(category)) {
                pageObject = bookRepository.findByPublisherContaining(search, pageable);
            }
        }else {
            if (category == null || "*".equals(category)) {
                pageObject = bookRepository.findAll(pageable);
            }
            if ("name".equals(category)) {
                pageObject = bookRepository.findByNameLike(search, pageable);
            }
            if ("author".equals(category)) {
                pageObject = bookRepository.findByAuthorLike(search, pageable);
            }
            if ("publishedYear".equals(category)) {
                pageObject = bookRepository.findByPublishedYearLike(search, pageable);
            }
            if ("publisher".equals(category)) {
                pageObject = bookRepository.findByPublisherLike(search, pageable);
            }
        }
        if(pageObject==null){
            responseObject.setStatus(StatusEnum.NOK.toString());
            responseObject.setMessage(ErrorMessage.BOOK_NOT_FOUND);
            return responseObject;
        }


        List<Map<String, Object>> resultList = new ArrayList<>();
        if(pageObject.hasContent()) {
            for (Book b: pageObject.getContent()) {
                Map<String, Object> bookMap = convertToMap(b);
                resultList.add(bookMap);
            }
        }
        Map<String, Object> pageResult = PageUtil.convertToPageObject(pageObject, resultList);
        responseObject.setStatus(StatusEnum.OK.toString());
        responseObject.setMessage(SuccessMessage.BOOK_FOUND);
        responseObject.setContent(pageResult);
        return responseObject;
    }

    @Override
    public ResponseObject specialSearch(String category, String searchText, Integer page, Integer size) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> resultMap = new HashMap<>();
        Pageable pageable = PageRequest.of(page-1, size);
        Page<truclam.library.truc_lam_library.entity.Page> pageObject = null;
        if(category!=null && "*".equals(category)) {
            pageObject = pageRepository.findByContentContaining(searchText, pageable);
        }else{
            pageObject = pageRepository.findByContentAndCategory(searchText, category, pageable);
        }
        if(pageObject.hasContent()){
            for (truclam.library.truc_lam_library.entity.Page p : pageObject.getContent()) {
                if(p.getBook() != null){
                    p.getBook().setTableContents(null);
                    if(p.getBook().getCategory()!=null){
                        p.getBook().getCategory().setBooks(null);
                    }
                }
            }

            responseObject.setStatus(StatusEnum.OK.toString());
            responseObject.setMessage(SuccessMessage.BOOK_FOUND);
            responseObject.setContent(resultMap);
        }else {
            responseObject.setStatus(StatusEnum.NOK.toString());
            responseObject.setMessage(ErrorMessage.BOOK_NOT_FOUND);
            return responseObject;
        }
        return responseObject;
    }

    Map<String, Object> convertToMap(Book book){
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> tableContentFormatted = findChildrenHeaders(book.getTableContents(), null);
        if(book.getTableContents()!=null && book.getTableContents().size()>0) {
            book.setTableContents(null);
        }
        if(book.getCategory() !=null){
            book.getCategory().setBooks(null);
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
