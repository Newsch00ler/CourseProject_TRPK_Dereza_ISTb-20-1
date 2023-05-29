package MVC;

import DB.Database;
import Model.Reader;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class TableReaderModel extends DefaultTableModel {
    private String[] columnName = {"Читательский билет", "ФИО", "Статус", "Телефон", "Email", "Адрес", "Паспорт"};
    private Database database;
    private int ID_library_card;
    private String full_name;
    private int ID_status;
    private String phone_number;
    private String email;
    private String address;
    private String passport;

    public TableReaderModel(Database database){
        this.database = database;
    }
    public TableReaderModel(String q, String w, String e, String r, String t, String y, String u){}

    public String[] getColumnName() {
        return columnName;
    }
    public int getColumnNameSize() {
        return columnName.length;
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

//    @Override
//    public Object getValueAt(int rowIndex, int columnIndex) {
//        Reader reader = database.getReaderList().get(rowIndex);
//        switch (columnIndex){
//            case 0:
//                return reader.getID_library_card(); //1 000 000 - 9 999 999
//            case 1:
//                return reader.getFull_name();
//            case 2:
//                return reader.getStatus();
//            case 3:
//                return reader.getPhone_number();
//            case 4:
//                return reader.getEmail();
//            case 5:
//                return reader.getAddress();
//            case 6:
//                return reader.getPassport();
//        }
//        return null;
//    }
//
//    @Override
//    public int getRowCount() {
//        return database.getSizeReaderList();
//    }
//
//    @Override
//    public int getColumnCount() {
//        return columnName.length;
//    }
//
//    @Override
//    public String getColumnName(int column){
//        return columnName[column];
//    }
//
//    public void update(){
//        fireTableDataChanged();
//    }
}
