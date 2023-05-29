package MVC;

import DB.Database;
import Model.LibraryCard;

import javax.swing.table.AbstractTableModel;

public class TableLibraryCardModel extends AbstractTableModel {
    //private String[] columnName = {"ID_library_card", "Книга", "Читатель", "Дата получение"};
    private String[] columnName = {"Номер читательского билета", "Читатель",};
    private Database database;

//    @Override
//    public Object getValueAt(int rowIndex, int columnIndex) {
//        LibraryCard libraryCard = database.getLibraryCardList().get(rowIndex);
//        switch (columnIndex){
//            case 0:
//                return libraryCard.getID_library_card();
//            case 1:
//                return libraryCard.getBook_name();
//            case 2:
//                return libraryCard.getID_reader();
//            case 3:
//                return libraryCard.getDate_receiving();
//        }
//        return null;
//    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LibraryCard libraryCard = database.getLibraryCardList().get(rowIndex);
        switch (columnIndex){
            case 0:
                return libraryCard.getID_library_card();
            case 1:
                return libraryCard.getID_reader();
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return database.getSizeLibraryCardList();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public String getColumnName(int column){
        return columnName[column];
    }

    public void update(){
        fireTableDataChanged();
    }
}