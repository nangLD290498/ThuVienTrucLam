package truclam.library.truc_lam_library.util;

import truclam.library.truc_lam_library.entity.TableContent;

import java.util.Map;

public class MapToObject {
    public static TableContent mapToTableContent(Map<String, Object> mapData){
        if(mapData==null || mapData.size()==0) return null;
        TableContent tableContent = new TableContent();
        tableContent.setHeaderContent(mapData.get("headerContent")!=null?mapData.get("headerContent").toString():null);
        tableContent.setFromPage(mapData.get("fromPage")!=null?Integer.parseInt(mapData.get("fromPage").toString()):null);
        tableContent.setToPage(mapData.get("toPage")!=null?Integer.parseInt(mapData.get("toPage").toString()):null);
        return tableContent;
    }
}
