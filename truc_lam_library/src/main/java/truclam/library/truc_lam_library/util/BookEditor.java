package truclam.library.truc_lam_library.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;
import truclam.library.truc_lam_library.entity.Book;

import java.beans.PropertyEditorSupport;
import java.io.IOException;

public class BookEditor extends PropertyEditorSupport {
    private ObjectMapper objectMapper;

    public BookEditor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) {
            setValue(null);
        } else {
            Book prod = new Book();
            try {
                prod = objectMapper.readValue(text, Book.class);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
            setValue(prod);
        }
    }
}
