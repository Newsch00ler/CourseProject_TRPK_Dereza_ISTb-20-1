package MVC;

import DB.Database;
import Model.LibraryCard;
import Model.Penalty;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class TablePenaltyModel extends DefaultTableModel {
    private String[] columnName = {"ID", "Читательский билет", "Наименование", "Размер штрафа"};
    private Database database;

    public TablePenaltyModel(Database database){
        this.database = database;
    }

    public TablePenaltyModel(String q, String w, String e, String r){}

    public String[] getColumnName() {
        return columnName;
    }
    public int getColumnNameSize() {
        return columnName.length;
    }
//    @Override
//    public Object getValueAt(int rowIndex, int columnIndex) {
//        Penalty penalty = database.getPenaltyList().get(rowIndex);
//        switch (columnIndex){
//            case 0:
//                return penalty.getID_penalty();
//            case 1:
//                return penalty.getID_library_card();
//            case 2:
//                return penalty.getName();
//            case 3:
//                return penalty.getFine();
//        }
//        return null;
//    }
//
//    @Override
//    public int getRowCount() {
//        return database.getSizePenaltyList();
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
