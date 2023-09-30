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
import truclam.library.truc_lam_library.entity.Category;
import truclam.library.truc_lam_library.entity.TableContent;
import truclam.library.truc_lam_library.repository.BookRepository;
import truclam.library.truc_lam_library.repository.CategoryRepository;
import truclam.library.truc_lam_library.repository.PageRepository;
import truclam.library.truc_lam_library.util.ObjectConvertor;
import truclam.library.truc_lam_library.util.PageUtil;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PageRepository pageRepository;

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public ResponseObject getBookDetails(Integer id) {
        ResponseObject responseObject = new ResponseObject();
        Optional<Book> b = bookRepository.findById(id);
        Integer count = pageRepository.countByBookId(id);
        if(b.isPresent()){
            responseObject.setStatus(StatusEnum.OK.toString());
            responseObject.setMessage(SuccessMessage.BOOK_FOUND);
            Map<String, Object> result = new HashMap<>();
            result.put("pageCount", count);
            result.put("book", ObjectConvertor.objectToMap(b.get()));
            responseObject.setContent(result);
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
    public ResponseObject getBooksByAuthor(Integer page, Integer pageSize, String author) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> resultMap;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Book> pageObject = bookRepository.findByAuthor(author, pageable);
        if (pageObject.hasContent()) {
            List<Map<String, Object>> bookList = new ArrayList<>();
            for (Book book : pageObject.getContent()) {
                Map<String, Object> bookMap = ObjectConvertor.objectToMap(book);
                bookList.add(bookMap);
            }
            resultMap = PageUtil.convertToPageObject(pageObject, bookList);
            responseObject.setStatus(StatusEnum.OK.toString());
            responseObject.setMessage(SuccessMessage.BOOK_FOUND);
            responseObject.setContent(resultMap);
        } else {
            responseObject.setStatus(StatusEnum.NOK.toString());
            responseObject.setMessage(ErrorMessage.BOOK_NOT_FOUND);
            return responseObject;
        }
        return responseObject;
    }

    @Override
    public ResponseObject getBooksByCate(Integer page,Integer size, Integer cateId) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> resultMap;
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Book> pageObject = bookRepository.findByCate(cateId, pageable);
        if (pageObject.hasContent()) {
            List<Map<String, Object>> bookList = new ArrayList<>();
            for (Book book : pageObject.getContent()) {
                Map<String, Object> bookMap = ObjectConvertor.objectToMap(book);
                bookList.add(bookMap);
            }
            resultMap = PageUtil.convertToPageObject(pageObject, bookList);
            responseObject.setStatus(StatusEnum.OK.toString());
            responseObject.setMessage(SuccessMessage.BOOK_FOUND);
            responseObject.setContent(resultMap);
        } else {
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
        Pageable pageable = PageRequest.of(page - 1, Constant.PAGE_SIZE);
        Page<Book> pageObject = bookRepository.findAll( pageable);
        if (pageObject.hasContent()) {
            List<Map<String, Object>> bookList = new ArrayList<>();
            for (Book book : pageObject.getContent()) {
                Map<String, Object> bookMap = ObjectConvertor.objectToMap(book);
                bookList.add(bookMap);
            }
            resultMap = PageUtil.convertToPageObject(pageObject, bookList);
            responseObject.setStatus(StatusEnum.OK.toString());
            responseObject.setMessage(SuccessMessage.BOOK_FOUND);
            responseObject.setContent(resultMap);
        } else {
            responseObject.setStatus(StatusEnum.NOK.toString());
            responseObject.setMessage(ErrorMessage.BOOK_NOT_FOUND);
            return responseObject;
        }
        return responseObject;
    }

    @Override
    public Page<List<Map<String, Object>>> getAuthors(Integer page, Integer pageSize) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> resultMap = new HashMap<>();
        Pageable pageable = PageRequest.of(page-1, pageSize);
        Page<List<Map<String, Object>>> pageObject = bookRepository.getAuthors(pageable);
        return pageObject;
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
    public Page<List<Map<String, Object>>> specialSearch(String category, String searchText, Integer page, Integer size) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> resultMap = new HashMap<>();
        Pageable pageable = PageRequest.of(page-1, size);
        Page<List<Map<String, Object>>> pageObject = null;
        if(category!=null && "*".equals(category)) {
            pageObject = pageRepository.findByContentContaining(searchText, pageable);
        }else{
            pageObject = pageRepository.findByContentAndCategory(searchText, category, pageable);
        }
        return pageObject;
    }

    @Override
    public ResponseObject getBooksWithCategories() {
        ResponseObject responseObject = new ResponseObject();
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, Object>> categories = categoryRepository.findAllForHome();
        for (Map<String, Object> c: categories) {
            Map<String, Object> categoryMap= new HashMap<>();
            List<Book> books = bookRepository.findFirst4ByCate((Integer) c.get("id"));
            for (Book b: books) {
                b.setTableContents(null);
                b.setCategory(null);
            }
            categoryMap.put("books", books);
            categoryMap.putAll(c);
            result.add(categoryMap);
        }
        if(result.size()>0) {
            responseObject.setStatus(StatusEnum.OK.toString());
            responseObject.setMessage(SuccessMessage.BOOK_FOUND);
            responseObject.setContent(result);
        }else{
            responseObject.setStatus(StatusEnum.NOK.toString());
            responseObject.setMessage(ErrorMessage.BOOK_NOT_FOUND);
            responseObject.setContent(new ArrayList<>());
        }
        return responseObject;
    }

}
