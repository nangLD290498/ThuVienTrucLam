package truclam.library.truc_lam_library.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import truclam.library.truc_lam_library.constant.ResponseObject;
import truclam.library.truc_lam_library.entity.Book;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface PdfService {
    ResponseObject savePdfByPage(String pdfName, String bookName);
    ResponseObject upload(MultipartFile multipartFile) throws IOException;

    ResponseObject saveBookInfor(Book book);

    ResponseObject saveContentTable(Map<String, Object> mapData);

    ResponseObject saveBookFullFlow(Book book, List<Map<String, Object>> headerlist, MultipartFile multipartFile) throws IOException;
}
