package dn.bookstore.entity;


public enum FileType {

    PDF(".pdf"),EPUB("epub"),FB2("fb2");

    private final String fileExtentionString;

    FileType(String fileExtentionString) {
        this.fileExtentionString = fileExtentionString;
    }


    public static String getExtentionStringByTypeId(Integer typeId){
        switch (typeId){
            case 1:return FileType.PDF.getExtentionStringByTypeId(typeId);
            case 2:return FileType.EPUB.getExtentionStringByTypeId(typeId);
            case 3:return FileType.FB2.getExtentionStringByTypeId(typeId);
            default:return "";
        }
     }
}

