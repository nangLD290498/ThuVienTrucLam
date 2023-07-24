package truclam.library.truc_lam_library.service;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import truclam.library.truc_lam_library.constant.*;
import truclam.library.truc_lam_library.entity.Book;
import truclam.library.truc_lam_library.entity.Page;
import truclam.library.truc_lam_library.entity.TableContent;
import truclam.library.truc_lam_library.repository.BookRepository;
import truclam.library.truc_lam_library.repository.CategoryRepository;
import truclam.library.truc_lam_library.repository.PageRepository;
import truclam.library.truc_lam_library.repository.TableContentRepository;
import truclam.library.truc_lam_library.util.FileUploadUtil;
import truclam.library.truc_lam_library.util.ObjectConvertor;

import java.io.IOException;
import java.util.*;

@Service
public class PdfServiceImpl implements PdfService{

    Logger logger = LoggerFactory.getLogger(PdfService.class);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PageRepository pageRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TableContentRepository tableContentRepository;

    @Value("${filePath}")
    String path;

    @Value("${splitting}")
    String splitting;

    public ResponseObject savePdfByPage(String pdfName, String bookName, Integer id) {
        ResponseObject respose = new ResponseObject();
        Book book = bookRepository.findByName(bookName);
        List<Page> pageList = new ArrayList<>();
        PdfReader reader = null;
        try {
            logger.info("path: {}", path);
            String filePath = path.concat(String.valueOf(book.getId())).concat(splitting).concat("pdf").concat(splitting);
            reader = new PdfReader(filePath.concat(pdfName).concat(".pdf"));
            int pages = reader.getNumberOfPages();
            for (int i = 1; i <= pages; i++) {
                StringBuilder text = new StringBuilder();
                text.append(PdfTextExtractor.getTextFromPage(reader, i));
                logger.info("length: {}", text.length());
                Page page = new Page();
                page.setPageNo(i);
                page.setBook(book);
                page.setContent(text.toString());
                pageList.add(page);
            }
            reader.close();
            logger.info("Saving book ....");
            pageRepository.saveAll(pageList);
            respose.setStatus(StatusEnum.OK.toString());
            respose.setMessage(SuccessMessage.SAVED_BOOK_CONTENT);
        } catch (Exception e){
            respose.setStatus(StatusEnum.NOK.toString());
            respose.setMessage(ErrorMessage.GENERAL_ERROR);
            e.printStackTrace();
        }
        return respose;
    }

    public ResponseObject upload(MultipartFile multipartFile, MultipartFile thumbnailPic, Book book) throws IOException {
        ResponseObject respose = new ResponseObject();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String picName = StringUtils.cleanPath(thumbnailPic.getOriginalFilename());
        if(!fileName.endsWith(".pdf")){
            respose.setStatus(StatusEnum.NOK.toString());
            respose.setMessage(ErrorMessage.NOT_PDF);
            return respose;
        }
        String filePath = path.concat(String.valueOf(book.getId())).concat(splitting).concat("pdf");
        String picPath = path.concat(String.valueOf(book.getId())).concat(splitting).concat("images");
        filePath = FileUploadUtil.saveFile(fileName, multipartFile, filePath);
        picPath = FileUploadUtil.saveFile(picName, thumbnailPic, picPath);

        //save path
        book.setPdfUrl(filePath);
        book.setThumbnailUrl(picPath);
        bookRepository.save(book);
        respose.setStatus(StatusEnum.OK.toString());
        respose.setMessage(SuccessMessage.SAVED_BOOK_INFO);
        Map<String, String> content = new HashMap<>();
        content.put("fileName", fileName);
        respose.setContent(content);
        return respose;
    }

    public ResponseObject saveBookInfor(Book book) {
        ResponseObject respose = new ResponseObject();
        //save book infor to db
        Book existing = bookRepository.findByName(book.getName());
        if(existing!=null){
            respose.setStatus(StatusEnum.NOK.toString());
            respose.setMessage(ErrorMessage.EXISTED_BOOK);
            return respose;
        }
        Book savedBook = bookRepository.save(book);
        respose.setStatus(StatusEnum.OK.toString());
        respose.setMessage(SuccessMessage.SAVED_BOOK_INFO);
        respose.setContent(savedBook);
        return respose;
    }

    public ResponseObject saveContentTable(Map<String, Object> mapData) {
        ResponseObject respose = new ResponseObject();
        String bookName = mapData.get("bookName").toString();
        List<Map<String, Object>> headerlist = (ArrayList) mapData.get("contentTable");
        Book book =bookRepository.findByName(bookName);
        if(book==null){
            respose.setStatus(StatusEnum.NOK.toString());
            respose.setMessage(ErrorMessage.BOOK_NOT_FOUND);
            return respose;
        }
        logger.info("saving headers of book: {}", book.toString());
        saveContentTable(headerlist, book, null);
        respose.setStatus(StatusEnum.OK.toString());
        respose.setMessage(SuccessMessage.SAVED_TABLE_CONTENT);
        return respose;
    }

    @Override
    public ResponseObject saveBookFullFlow(Book book, List<Map<String, Object>> headerlist, MultipartFile multipartFile, MultipartFile thumbnailPic) throws IOException {
        ResponseObject respose = new ResponseObject<>();

        logger.info("Saving book infor...");
        ResponseObject resSave = saveBookInfor(book);
        if(resSave.getStatus().equals(StatusEnum.NOK.toString())) return resSave;

        logger.info("uploading pdf...");
        ResponseObject res = upload(multipartFile, thumbnailPic, (Book) resSave.getContent());
        if(res.getStatus().equals(StatusEnum.NOK.toString())) return res;
        String fileName = ((Map<String, String>) res.getContent()).get("fileName");

        logger.info("Saving book content...");
        res = savePdfByPage(fileName.substring(0, fileName.length()-4), book.getName(), book.getId());
        if(res.getStatus().equals(StatusEnum.NOK.toString())) return res;

        logger.info("saving headers of book...");
        saveContentTable(headerlist, (Book) resSave.getContent(), null);

        respose.setStatus(StatusEnum.OK.toString());
        respose.setMessage(SuccessMessage.SAVED_BOOK);
        respose.setContent(resSave.getContent());
        return respose;
    }

    @Override
    public ResponseObject getPDF(Integer id) {
        ResponseObject responseObject = new ResponseObject();
        Optional<Book> b = bookRepository.findById(id);
        if(b.isPresent()){
            responseObject.setStatus(StatusEnum.OK.toString());
            responseObject.setMessage(SuccessMessage.PDF_FOUND);
            responseObject.setContent(b.get().getPdfUrl());
            logger.info("pdf found : {}",  b.get().getRemarks());
        }else{
            responseObject.setStatus(StatusEnum.NOK.toString());
            responseObject.setMessage(ErrorMessage.BOOK_NOT_FOUND);
            logger.info("no book found");
        }
        return responseObject;
    }

    public void saveContentTable(List<Map<String, Object>> childs,Book book , TableContent parent){
        List<TableContent> tableContents = new ArrayList<>();
        for (Map<String, Object> item: childs) {
            TableContent header = ObjectConvertor.mapToTableContent(item);
            if(header == null) continue;
            header.setParent(parent);
            header.setBook(book);
            tableContents.add(header);
            logger.info("saving header: {}", header.toString());
        }
        List<TableContent> savedHeaders = null;
        if(tableContents.size()>0) {
            savedHeaders = tableContentRepository.saveAll(tableContents);
        }

        for (Map<String, Object> item: childs) {
            if(savedHeaders == null) continue;
            TableContent header = ObjectConvertor.mapToTableContent(item);
            if(header==null) continue;
            TableContent subParent = getParentHeader(savedHeaders, header);
            if(item.containsKey("childs")){
                List<Map<String, Object>> subList = (ArrayList<Map<String, Object>>) item.get("childs");
                if(subList==null||subList.size()==0) continue;
                saveContentTable(subList,book , subParent);
            }
        }
    }

    public TableContent getParentHeader(List<TableContent> savedHeaders, TableContent header){
        for (TableContent tc: savedHeaders) {
            String tcHeader = Optional.of(tc.getHeaderContent()).orElse("");
            String hHeader = Optional.of(header.getHeaderContent()).orElse("");
            int tcFromPage = tc.getFromPage()==null?-1: tc.getFromPage();
            int hFromPage = header.getFromPage()==null?-1: header.getFromPage();
            int tcToPage = tc.getToPage()==null?-1: tc.getToPage();
            int hToPage = header.getToPage()==null?-1: header.getToPage();
            if(tcHeader.equals(hHeader) && tcFromPage==hFromPage && tcToPage==hToPage){
                return tc;
            }
        }
        return null;
    }
}
