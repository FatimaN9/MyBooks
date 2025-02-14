package com.example.mybooks;


public class DataClass {
    private String dataTitle;
    private String dataDesc;
    private String dataLang;

    private String key;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getDataTitle() {
        return dataTitle;
    }
    public String getDataDesc() {
        return dataDesc;
    }
    public String getDataLang() {
        return dataLang;
    }

    public DataClass(String bookTitle, String bookDesc, String bookLang) {
        this.dataTitle = bookTitle;
        this.dataDesc = bookDesc;
        this.dataLang = bookLang;

    }
    public DataClass(){

    }
}
