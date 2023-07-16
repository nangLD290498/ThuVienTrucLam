package truclam.library.truc_lam_library.constant;

import lombok.Data;

public enum StatusEnum {
    OK("ok"),
    NOK("nok");

    String value;
    StatusEnum(String value) {
    }

    private String getvalue() { return value; }

}
