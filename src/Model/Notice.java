package Model;

public class Notice {
    private int ID_notice;
    private int ID_library_card;
    private String message;
    private int ID_penalty;

    public Notice(int ID_notice, int ID_library_card, String message){
        this.ID_notice = ID_notice;
        this.ID_library_card = ID_library_card;
        this.message = message;
    }

    public int getID_notice() {
        return ID_notice;
    }
    public int getID_library_card() {
        return ID_library_card;
    }
    public String getMessage() {
        return message;
    }
    public int getID_penalty() {
        return ID_penalty;
    }

    public void setID_notice(int ID_notice) {
        this.ID_notice = ID_notice;
    }
    public void setID_library_card(int ID_library_card) {
        this.ID_library_card = ID_library_card;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setID_penalty(int ID_penalty) {
        this.ID_penalty = ID_penalty;
    }
}
