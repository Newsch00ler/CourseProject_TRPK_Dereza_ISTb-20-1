package MVC;

import DB.Database;

import javax.swing.table.DefaultTableModel;

public class TablePenaltyModel extends DefaultTableModel {
    private String[] columnName = {"ID", "Читательский билет", "Наименование", "Размер штрафа"};
    private Database database;

    public TablePenaltyModel(Database database){
        this.database = database;
    }

    public String[] getColumnName() {
        return columnName;
    }
    public int getColumnNameSize() {
        return columnName.length;
    }
}
