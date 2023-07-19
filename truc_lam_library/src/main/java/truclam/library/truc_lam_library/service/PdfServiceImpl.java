package truclam.library.truc_lam_library.service;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import truclam.library.truc_lam_library.constant.*;
import truclam.library.truc_lam_library.controller.PdfController;
import truclam.library.truc_lam_library.entity.Book;
import truclam.library.truc_lam_library.entity.Page;
import truclam.library.truc_lam_library.entity.TableContent;
import truclam.library.truc_lam_library.repository.BookRepository;
import truclam.library.truc_lam_library.repository.PageRepository;
import truclam.library.truc_lam_library.repository.TableContentRepository;
import truclam.library.truc_lam_library.util.FileUploadUtil;
import truclam.library.truc_lam_library.util.MapToObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PdfServiceImpl implements PdfService{

    Logger logger = LoggerFactory.getLogger(PdfService.class);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PageRepository pageRepository;

    @Autowired
    TableContentRepository tableContentRepository;

    @Value("${filePath}")
    String path;

    @Autowired
    private ResourceLoader resourceLoader;
    @Override
    public ResponseObject savePdfByPage(String pdfName, String bookName) {
        ResponseObject respose = new ResponseObject();
        Book book = bookRepository.findByName(bookName);
        List<Page> pageList = new ArrayList<>();
        PdfReader reader = null;
        try {
            logger.info("path: {}", path);
            reader = new PdfReader(path.concat(pdfName).concat(".pdf"));
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

    @Override
    public ResponseObject uploadAndSavePdfByPage(MultipartFile multipartFile) throws IOException {
        ResponseObject respose = new ResponseObject();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if(!fileName.endsWith(".pdf")){
            respose.setStatus(StatusEnum.NOK.toString());
            respose.setMessage(ErrorMessage.NOT_PDF);
            return respose;
        }
        String filecode = FileUploadUtil.saveFile(fileName, multipartFile, path);

        respose.setStatus(StatusEnum.OK.toString());
        respose.setMessage(SuccessMessage.SAVED_BOOK_INFO);
        return respose;
    }

    @Override
    public ResponseObject saveBookInfor(Book book) {
        ResponseObject respose = new ResponseObject();
        //save book infor to db
        Book existing = bookRepository.findByName(book.getName());
        if(existing!=null){
            respose.setStatus(StatusEnum.NOK.toString());
            respose.setMessage(ErrorMessage.EXISTED_BOOK);
            return respose;
        }
        bookRepository.save(book);
        respose.setStatus(StatusEnum.OK.toString());
        respose.setMessage(SuccessMessage.SAVED_BOOK_INFO);
        return respose;
    }

    @Override
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

    public void saveContentTable(List<Map<String, Object>> childs,Book book , TableContent parent){
        List<TableContent> tableContents = new ArrayList<>();
        for (Map<String, Object> item: childs) {
            TableContent header = MapToObject.mapToTableContent(item);
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
            TableContent header = MapToObject.mapToTableContent(item);
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
