package truclam.library.truc_lam_library.util;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
    public static String saveFile(String fileName, MultipartFile multipartFile, String path)
            throws IOException {
        Path uploadPath = Paths.get(path);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            //path = filePath.toFile().getCanonicalPath();
        } catch (IOException ioe) {
            throw new IOException("File đã tồn tại " + fileName, ioe);
        }

        return path;
    }
}