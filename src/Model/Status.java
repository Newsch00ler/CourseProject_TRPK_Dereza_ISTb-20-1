package Model;

public class Status {
    private int ID_status;
    private String status_value;

    public Status(int ID_status, String status_value){
        this.ID_status = ID_status;
        this.status_value = status_value;
    }

    public int getID_status() {
        return ID_status;
    }
    public String getStatus_value() {
        return status_value;
    }

    public void setID_status(int ID_status) {
        this.ID_status = ID_status;
    }
    public void setStatus_value(String status_value) {
        this.status_value = status_value;
    }
}
