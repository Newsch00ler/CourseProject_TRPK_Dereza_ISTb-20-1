package Model;

public class Reader {
    private int ID_reader;
    private int ID_library_card;
    private String full_name;
    private int ID_status;
    private String phone_number;
    private String email;
    private String address;
    private String passport;

    public Reader(int ID_library_card, String full_name, int ID_status, String phone_number, String email, String address, String passport){
        this.ID_library_card = ID_library_card;
        this.full_name = full_name;
        this.address = address;
        this.passport = passport;
        this.phone_number = phone_number;
        this.email= email;
        this.ID_status = ID_status;
    }

    public Reader(String full_name, int ID_status, String phone_number, String email, String address, String passport){
        this.full_name = full_name;
        this.address = address;
        this.passport = passport;
        this.phone_number = phone_number;
        this.email= email;
        this.ID_status = ID_status;
    }

    public int getID_reader() {
        return ID_reader;
    }
    public int getID_library_card() {
        return ID_library_card;
    }
    public String getFull_name() {
        return full_name;
    }
    public int getID_status() {
        return ID_status;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
    public String getPassport() {
        return passport;
    }

    public void setID_reader(int ID_reader) {
        this.ID_reader = ID_reader;
    }
    public void setID_library_card(int ID_library_card) {
        this.ID_library_card = ID_library_card;
    }
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPassport(String passport) {
        this.passport = passport;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setID_status(int ID_status) {
        this.ID_status = ID_status;
    }
}
