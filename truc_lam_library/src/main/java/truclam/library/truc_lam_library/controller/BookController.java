package truclam.library.truc_lam_library.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import truclam.library.truc_lam_library.constant.Constant;
import truclam.library.truc_lam_library.constant.ResponseObject;
import truclam.library.truc_lam_library.entity.Book;
import truclam.library.truc_lam_library.service.BookService;
import truclam.library.truc_lam_library.service.PdfService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {
    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    @Autowired
    PdfService pdfService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseObject saveBookFullFlow(@RequestParam String bookString,
                                           @RequestParam String tableContent,
                                           @RequestParam("file") MultipartFile multipartFile,
                                           @RequestParam("thumbnailPic") MultipartFile thumbnailPic
    ) throws IOException {
        //Base64 to json string
        byte[] decodedBytesBook = Base64.getDecoder().decode(bookString);
        bookString = new String(decodedBytesBook);

        byte[] decodedBytesTableContent = Base64.getDecoder().decode(tableContent);
        tableContent = new String(decodedBytesTableContent);

        ObjectMapper mapper = new ObjectMapper();
        Book book = mapper.readValue(bookString, Book.class);
        List<Map<String, Object>> tableContentList = mapper.readValue(tableContent, new TypeReference<List<Map<String, Object>>>(){});
        return pdfService.saveBookFullFlow(book, tableContentList, multipartFile, thumbnailPic);
    }

    @GetMapping("/{id}")
    public ResponseObject getBookByid(@PathVariable Integer id) {
        return bookService.getBookDetails(id);
    }

    @GetMapping("/{page}/{size}/{order}/{column}")
    public ResponseObject getBookList(@RequestParam(required = false) String category,
                                        @RequestParam(required = false) String search,
                                        @RequestParam(required = false) String isContaining,
                                        @PathVariable Integer page,
                                        @PathVariable Integer size,
                                        @PathVariable String order,
                                        @PathVariable String column) {
        return bookService.getList(category, search, page, size, order, column, isContaining);
    }

    @GetMapping("/specialSearch/{page}")
    public Page<List<Map<String, Object>>> specialSearch(@RequestParam String searchText,
                                                         @PathVariable Integer page,
                                                         @RequestParam String category) {
        return bookService.specialSearch(category ,searchText, page, Constant.PAGE_SIZE);
    }

    @GetMapping("/ByCategory/{cateId}")
    public ResponseObject getBooksByCate(@RequestParam Integer page,
                                   @PathVariable Integer cateId) {
        return bookService.getBooksByCate(page, Constant.PAGE_SIZE,  cateId);
    }

    @GetMapping("/ByAuthor")
    public ResponseObject getBooksByAuthor(@RequestParam Integer page,
                                         @RequestParam String author) {
        return bookService.getBooksByAuthor(page, Constant.PAGE_SIZE,  author);
    }

    @GetMapping("getBooksWithCategories")
    public ResponseObject getBooksWithCategories() {
        return bookService.getBooksWithCategories();
    }

    @GetMapping
    public ResponseObject getBooks(@RequestParam Integer page) {
        return bookService.getBooks(page);
    }

    @GetMapping(value = "/{id}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPdfByid(@PathVariable Integer id) throws IOException {
        Book book = bookService.getById(id);
        String pdfLocalPath = book.getPdfUrl();

        Path pdfPath = Paths.get(pdfLocalPath);
        byte[] pdf = Files.readAllBytes(pdfPath);

        HttpHeaders headers = new HttpHeaders();
        String fileName = "example.pdf";
        headers.setContentDispositionFormData(fileName, fileName);
        headers.setContentType(MediaType.APPLICATION_PDF);
        return ResponseEntity.ok().headers(headers).body(pdf);
    }

    @GetMapping(value = "/{id}/thumbnail", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody ResponseEntity<InputStreamResource> getThumbnailByid(@PathVariable Integer id) throws IOException {
        Book book = bookService.getById(id);
        String thumbnailUrl = book.getThumbnailUrl();

        boolean jpg = false;
        MediaType contentType = jpg ? MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG;
        Path thumbnailPath = Paths.get(thumbnailUrl);
        InputStream inputStream = Files.newInputStream(thumbnailPath);
        return ResponseEntity.ok()
                .contentType(contentType)
                .body(new InputStreamResource(inputStream));
    }
}
