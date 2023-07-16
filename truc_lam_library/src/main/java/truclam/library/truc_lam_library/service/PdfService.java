package truclam.library.truc_lam_library.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import truclam.library.truc_lam_library.constant.ResponseObject;

import java.io.IOException;

@Service
public interface PdfService {
    ResponseObject savePdfByPage(String pdfName, String bookName);
    ResponseObject uploadAndSavePdfByPage(MultipartFile multipartFile) throws IOException;
}
