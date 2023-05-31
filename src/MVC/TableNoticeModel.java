package MVC;

import DB.Database;

import javax.swing.table.DefaultTableModel;

public class TableNoticeModel extends DefaultTableModel {
    private String[] columnName = {"ID", "Читательский билет", "Сообщение"};
    private Database database;

    public TableNoticeModel(Database database){
        this.database = database;
    }

    public String[] getColumnName() {
        return columnName;
    }
    public int getColumnNameSize() {
        return columnName.length;
    }
}
