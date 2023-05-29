package Model;

public class Book {
    private int ID_book;
    private int ID_library_card;
    private int ID_status;
    private String name;
    private String author;
    private int year;
    private int number_pages;
    private String chapter; //раздел
    private String genre; //жанр
    private String inventory_number;
    private String cipher; //шифр
    private String date_receiving;


    public Book(int ID_book, int ID_library_card, int ID_status, String name, String author, int year, int number_pages, String chapter, String genre, String inventory_number, String cipher){
        this.ID_book = ID_book;
        this.ID_library_card = ID_library_card;
        this.ID_status = ID_status;
        this.name = name;
        this.author = author;
        this.year = year;
        this.number_pages = number_pages;
        this.chapter = chapter;
        this.genre = genre;
        this.inventory_number = inventory_number;
        this.cipher = cipher;
    }

    public int getID_book() {
        return ID_book;
    }
    public int getID_library_card() {
        return ID_library_card;
    }
    public int getID_status() {
        return ID_status;
    }
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
    public int getYear() {
        return year;
    }
    public int getNumber_pages() {
        return number_pages;
    }
    public String getChapter() {
        return chapter;
    }
    public String getGenre() {
        return genre;
    }
    public String getInventory_number() {
        return inventory_number;
    }
    public String getCipher() {
        return cipher;
    }
    public String getDate_receiving() {
        return date_receiving;
    }

    public void setID_book(int ID_book) {
        this.ID_book = ID_book;
    }
    public void setID_library_card(int ID_library_card) {
        this.ID_library_card = ID_library_card;
    }
    public void setID_status(int ID_status) {
        this.ID_status = ID_status;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setNumber_pages(int number_pages) {
        this.number_pages = number_pages;
    }
    public void setChapter(String chapter) {
        this.chapter = chapter;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setInventory_number(String inventory_number) {
        this.inventory_number = inventory_number;
    }
    public void setCipher(String cipher) {
        this.cipher = cipher;
    }
    public void setDate_receiving(String date_receiving) {
        this.date_receiving = date_receiving;
    }
}
