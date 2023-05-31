package App;

import DB.Database;
import MVC.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database database = new Database();
        try{
            database.initDB();
//            database.createDB();
//            database.addReader(new Reader("ываывыв",1, "Field 2","Field 1", "Field 2","Field 1"));
//            database.addReader(new Reader(1, "Дереза", 1, "+79123456789", "email1@mail.ru", "Иркутск", "1234 123456"));
//            database.addReader(new Reader(2, "Попович", 2, "+79111222333", "email2@mail.ru", "Ангарск", "1111 222222"));
//            database.addReader(new Reader(3, "Потапов", 1, "+79222333444", "email3@mail.ru", "Иркутск-2", "2222 333333"));
//            database.addLibraryCard(new LibraryCard(1000000, 1));
//            database.addLibraryCard(new LibraryCard(1000001, 2));
//            database.addLibraryCard(new LibraryCard(1000002, 3));
            database.closeDB();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        TableReaderModel tableReaderModel = new TableReaderModel(database);
        TablePenaltyModel tablePenaltyModel = new TablePenaltyModel(database);
        TableNoticeModel tableNoticeModel = new TableNoticeModel(database);
        View mainView = new View();
        mainView.displayTable(tableReaderModel, database);
        mainView.setLocationRelativeTo(null);
        Controller controller = new Controller(tableReaderModel, tablePenaltyModel, tableNoticeModel);
        controller.execute(database, mainView);
    }
}