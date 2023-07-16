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
import truclam.library.truc_lam_library.repository.BookRepository;
import truclam.library.truc_lam_library.repository.PageRepository;
import truclam.library.truc_lam_library.util.FileUploadUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfServiceImpl implements PdfService{

    Logger logger = LoggerFactory.getLogger(PdfService.class);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PageRepository pageRepository;

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
            respose.setMessage(SuccessMessage.SAVED_BOOK);
        } catch (Exception e){
            respose.setStatus(StatusEnum.NOK.toString());
            respose.setMessage(ErrorMessage.GENERAL_ERROR);
            e.printStackTrace();
        }
        return respose;
    }

    @Override
    public ResponseObject uploadAndSavePdfByPage(MultipartFile multipartFile)  {
        ResponseObject respose = new ResponseObject();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        try {
            String filecode = FileUploadUtil.saveFile(fileName, multipartFile, path);
        } catch (IOException e) {
            respose.setStatus(StatusEnum.NOK.toString());
            respose.setMessage(ErrorMessage.EXISTED_BOOK);
            return respose;
        }

        if(!fileName.endsWith(".pdf")){
            respose.setStatus(StatusEnum.NOK.toString());
            respose.setMessage(ErrorMessage.NOT_PDF);
            return respose;
        }
        fileName = fileName.substring(0, fileName.length() -4);
        return savePdfByPage(fileName, fileName);
    }
}
