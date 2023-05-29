package Model;

public class LibraryCard {
    private int ID_library_card;
    //private String date_receiving;
    private int ID_reader;
    //private int book_name;


    public LibraryCard(int ID_library_card, int ID_reader){
        this.ID_library_card = ID_library_card;
        this.ID_reader = ID_reader;
    }

    public int getID_library_card() {
        return ID_library_card;
    }
//    public String getDate_receiving() {
//        return date_receiving;
//    }
    public int getID_reader() {
        return ID_reader;
    }
//    public int getBook_name() {
//        return book_name;
//    }

    public void setID_library_card(int ID_library_card) {
        this.ID_library_card = ID_library_card;
    }
//    public void setDate_receiving(String date_receiving) {
//        this.date_receiving = date_receiving;
//    }
    public void setID_reader(int ID_reader) {
        this.ID_reader = ID_reader;
    }
//    public void setBook_name(int book_name) {
//        this.book_name = book_name;
//    }
}
