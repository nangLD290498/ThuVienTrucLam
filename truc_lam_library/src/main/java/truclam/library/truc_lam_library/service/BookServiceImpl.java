package truclam.library.truc_lam_library.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import truclam.library.truc_lam_library.constant.*;
import truclam.library.truc_lam_library.entity.Book;
import truclam.library.truc_lam_library.entity.TableContent;
import truclam.library.truc_lam_library.repository.BookRepository;
import truclam.library.truc_lam_library.repository.PageRepository;
import truclam.library.truc_lam_library.util.ObjectConvertor;
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
            responseObject.setContent(ObjectConvertor.objectToMap(b.get()));
            logger.info("book found : {}",  b.get());
        }else{
            responseObject.setStatus(StatusEnum.NOK.toString());
            responseObject.setMessage(ErrorMessage.BOOK_NOT_FOUND);
            logger.info("no book found");
        }
        return responseObject;
    }

    @Override
    public Book getById(Integer id) {
        return bookRepository.findById(id).get();
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
                Map<String, Object> bookMap = ObjectConvertor.objectToMap(b);
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

    @Override
    public ResponseObject getBooks(Integer page) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> resultMap;
        Pageable pageable = PageRequest.of(page-1, Constant.PAGE_SIZE);
        Page<Book> pageObject = bookRepository.findAll(pageable);
        if(pageObject.hasContent()){
            List<Map<String, Object>> bookList = new ArrayList<>();
            for (Book book : pageObject.getContent()) {
                Map<String, Object> bookMap = ObjectConvertor.objectToMap(book);
                bookList.add(bookMap);
            }
            resultMap = PageUtil.convertToPageObject(pageObject, bookList);
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

}
