package truclam.library.truc_lam_library.constant;

import lombok.Data;

@Data
public class ResponseObject<T> {
    String status;
    String message;
    T  content;
}
