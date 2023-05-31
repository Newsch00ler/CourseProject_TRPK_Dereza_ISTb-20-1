package MVC;

import DB.Database;

import javax.swing.table.DefaultTableModel;

public class TableReaderModel extends DefaultTableModel {
    private String[] columnName = {"Читательский билет", "ФИО", "Статус", "Телефон", "Email", "Адрес", "Паспорт"};
    private Database database;

    public TableReaderModel(Database database){
        this.database = database;
    }

    public String[] getColumnName() {
        return columnName;
    }
    public int getColumnNameSize() {
        return columnName.length;
    }
}
