package truclam.library.truc_lam_library.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import truclam.library.truc_lam_library.constant.ResponseObject;
import truclam.library.truc_lam_library.entity.Book;
import truclam.library.truc_lam_library.service.PdfService;

import java.io.IOException;
import java.util.Base64;
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

    @PostMapping("/pdf/upload")
    public ResponseObject upload(@RequestParam("file") MultipartFile multipartFile ) throws IOException {
        return pdfService.upload(multipartFile);
    }

    @PostMapping("/pdf/saveBookInfor")
    public ResponseObject saveBookInfor(@RequestBody Book book) {
        logger.info("execute saveBookInfor api");
        return pdfService.saveBookInfor( book);
    }

    @PostMapping("/pdf/saveContentTable")
    public ResponseObject saveContentTable(@RequestBody Map<String, Object> mapData) {
        return pdfService.saveContentTable(mapData);
    }

    @PostMapping("/pdf/saveBookFullFlow")
    public ResponseObject saveBookFullFlow(@RequestParam String bookString,
                                           @RequestParam String tableContent,
                                           @RequestParam("file") MultipartFile multipartFile) throws IOException {
        //Base64 to json string
        byte[] decodedBytesBook = Base64.getDecoder().decode(bookString);
        bookString = new String(decodedBytesBook);

        byte[] decodedBytesTableContent = Base64.getDecoder().decode(tableContent);
        tableContent = new String(decodedBytesTableContent);

        ObjectMapper mapper = new ObjectMapper();
        Book book = mapper.readValue(bookString, Book.class);
        List<Map<String, Object>> tableContentList = mapper.readValue(tableContent, new TypeReference<List<Map<String, Object>>>(){});
        return pdfService.saveBookFullFlow(book, tableContentList, multipartFile);
    }

    @GetMapping("/pdf/get/{id}")
    public ResponseObject getPdf(@PathVariable Integer id) {
        return pdfService.getPDF(id);
    }

}
