package truclam.library.truc_lam_library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import truclam.library.truc_lam_library.constant.ResponseObject;
import truclam.library.truc_lam_library.entity.Book;
import truclam.library.truc_lam_library.service.PdfService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class PdfController {
    Logger logger = LoggerFactory.getLogger(PdfController.class);

    @Autowired
    PdfService pdfService;

    @PostMapping("/pdf/savePdfByPage")
    public ResponseObject savePdfByPage(@RequestBody Map<String, String> map) {
        return pdfService.savePdfByPage(map.get("pdfName"), map.get("bookName"));
    }

    @PostMapping("/pdf/uploadAndSavePdfByPage")
    public ResponseObject uploadAndSavePdfByPage(@RequestParam("file") MultipartFile multipartFile ) throws IOException {
        return pdfService.uploadAndSavePdfByPage(multipartFile);
    }

    @PostMapping("/pdf/saveBookInfor")
    public ResponseObject saveBookInfor(@RequestBody Book book) {
        logger.info("execute saveBookInfor api");
        return pdfService.saveBookInfor( book);
    }

    @PostMapping("/pdf/saveContentTable")
    public ResponseObject saveContentTable(@RequestBody List<Map<String, Object>> mapData) {
        return pdfService.saveContentTable(mapData);
    }
}
