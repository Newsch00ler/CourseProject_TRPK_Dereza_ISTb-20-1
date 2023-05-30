package DB;

import Model.*;

import java.util.ArrayList;

public class Database extends DBQueries{
    private ArrayList<Reader> readerList = new ArrayList<>();
    private ArrayList<Penalty> penaltyList = new ArrayList<>();
    private ArrayList<Notice> noticeList = new ArrayList<>();

    public ArrayList<Reader> getReadersList(){
        return readerList;
    }
    public void setReadersList(ArrayList<Reader> readerList) {
        this.readerList = readerList;
    }
    public int getSizeReaderList(){
        return readerList.size();
    }

    public ArrayList<Penalty> getPenaltyList() {
        return penaltyList;
    }
    public void setPenaltyList(ArrayList<Penalty> penaltyList) {
        this.penaltyList = penaltyList;
    }
    public int getSizePenaltyList(){
        return readerList.size();
    }

    public ArrayList<Notice> getNoticeList() {
        return noticeList;
    }
    public void setNoticeList(ArrayList<Notice> noticeList) {
        this.noticeList = noticeList;
    }
    public int getSizeNoticeList(){
        return readerList.size();
    }
}
