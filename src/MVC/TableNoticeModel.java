package MVC;

import DB.Database;

import javax.swing.table.DefaultTableModel;

public class TableNoticeModel extends DefaultTableModel {
    private String[] columnName = {"ID", "Читательский билет", "Сообщение"};
    private Database database;

    public TableNoticeModel(Database database){
        this.database = database;
    }

    public TableNoticeModel(String q, String w, String e){}

    public String[] getColumnName() {
        return columnName;
    }
    public int getColumnNameSize() {
        return columnName.length;
    }

//    @Override
//    public Object getValueAt(int rowIndex, int columnIndex) {
//        Notice notice = database.getNoticeList().get(rowIndex);
//        Reader reader = null;
//        switch (columnIndex){
//            case 0:
//                return notice.getID_notice();
//            case 1:
//                return notice.getID_library_card();
//            case 2:
//                return notice.getMessage();
//        }
//        return null;
//    }
//
//    @Override
//    public int getRowCount() {
//        return database.getSizeNoticeList();
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
