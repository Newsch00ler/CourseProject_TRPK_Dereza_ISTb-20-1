package Model;

public class Penalty {
    private int ID_penalty;
    private int ID_library_card;

    private String name;
    private double fine;
//
//    public Penalty(int ID_penalty, int ID_library_card, int ID_status, String name, double fine){
//        this.ID_penalty = ID_penalty;
//        this.ID_library_card = ID_library_card;
//        this.ID_status = ID_status;
//        this.name = name;
//        this.fine = fine;
//    }

//    public Penalty(int ID_penalty, int ID_reader, String name, double fine){
//        this.ID_penalty = ID_penalty;
//        this.ID_reader = ID_reader;
//        this.name = name;
//        this.fine = fine;
//    }

    public Penalty(int ID_penalty, int ID_library_card, String name, double fine){
        this.ID_penalty = ID_penalty;
        this.ID_library_card = ID_library_card;
        this.name = name;
        this.fine = fine;
    }

    public int getID_penalty() {
        return ID_penalty;
    }
    public int getID_library_card() {
        return ID_library_card;
    }
//    public int getID_reader() {
//        return ID_reader;
//    }
//    public int getID_status() {
//        return ID_status;
//    }
    public String getName() {
        return name;
    }
    public double getFine() {
        return fine;
    }


    public void setID_penalty(int ID_penalty) {
        this.ID_penalty = ID_penalty;
    }
    public void setID_library_card(int ID_library_card) {
        this.ID_library_card = ID_library_card;
    }
//    public void setID_reader(int ID_reader) {
//        this.ID_reader = ID_reader;
//    }
//    public void setID_status(int ID_status) {
//        this.ID_status = ID_status;
//    }
    public void setName(String name) {
        this.name = name;
    }
    public void setFine(double fine) {
        this.fine = fine;
    }
}
