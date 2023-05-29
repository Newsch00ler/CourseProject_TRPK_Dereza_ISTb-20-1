package DB;

import Model.*;

import java.sql.*;
import java.util.ArrayList;

public class DBQueries {
    private static final String PATH_TO_DB_FILE = "library.db";
    private static final String URL = "jdbc:sqlite:" + PATH_TO_DB_FILE;
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public void initDB() throws SQLException {
        con = DriverManager.getConnection(URL);
        if(con != null){
            DatabaseMetaData metaData = con.getMetaData();
        }
    }

    public void createDB() throws SQLException, Exception{
        stmt = con.prepareStatement("CREATE TABLE if not exists Status (" +
                "ID_status INTEGER PRIMARY KEY UNIQUE NOT NULL CHECK(ID_status > 0)," +
                "status_value TEXT NOT NULL);");
        stmt.execute();
        stmt = con.prepareStatement("CREATE TABLE if not exists Readers (" +
                "ID_reader INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL CHECK(ID_reader > 0)," +
                "ID_library_card INTEGER CHECK(ID_library_card >= 1000000 and ID_library_card <= 9999999)," +
                "full_name TEXT NOT NULL," +
                "ID_status INTEGER NOT NULL, " +
                "phone_number TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "address TEXT NOT NULL," +
                "passport TEXT NOT NULL," +
                //"FOREIGN KEY (ID_library_card) REFERENCES LibraryCards(ID_library_card)," +
                "FOREIGN KEY (ID_status) REFERENCES Status(ID_status));");
        stmt.execute();
//        stmt = con.prepareStatement("CREATE TABLE if not exists LibraryCards (" +
//                "ID_library_card INTEGER PRIMARY KEY UNIQUE NOT NULL CHECK((ID_library_card >= 1000000 and ID_library_card <= 9999999))," +
//                "ID_reader INTEGER NOT NULL CHECK(ID_reader > 0)," +
//                "date_receiving TEXT," +
//                "book_name TEXT," +
//                "FOREIGN KEY (ID_reader) REFERENCES Readers(ID_reader)," +
//                "FOREIGN KEY (ID_book) REFERENCES Books(ID_book));");
//        stmt.execute();
        stmt = con.prepareStatement("CREATE TABLE if not exists LibraryCards (" +
                "ID_library_card INTEGER PRIMARY KEY UNIQUE NOT NULL CHECK((ID_library_card >= 1000000 and ID_library_card <= 9999999))," +
                "ID_reader INTEGER CHECK(ID_reader > 0)," +
                "FOREIGN KEY (ID_reader) REFERENCES Readers(ID_reader));");
        stmt.execute();
//        stmt = con.prepareStatement("CREATE TABLE if not exists Books (" +
//                "ID_book INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL CHECK(ID_book > 0)," +
//                "ID_library_card INTEGER NOT NULL CHECK(ID_library_card >= 1000000 and ID_library_card <= 9999999)," +
//                "ID_status INTEGER NOT NULL," +
//                "name TEXT NOT NULL," +
//                "author TEXT NOT NULL," +
//                "year INTEGER NOT NULL," +
//                "number_pages INTEGER NOT NULL," +
//                "chapter TEXT NOT NULL," +
//                "genre TEXT NOT NULL," +
//                "inventory_numberT EXT NOT NULL," +
//                "cipher TEXT NOT NULL," +
//                "FOREIGN KEY (ID_status) REFERENCES Status(ID_status));");
//        stmt.execute();
        stmt = con.prepareStatement("CREATE TABLE if not exists Books (" +
                "ID_book INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL CHECK(ID_book > 0)," +
                "ID_library_card INTEGER NOT NULL CHECK(ID_library_card >= 1000000 and ID_library_card <= 9999999)," +
                "ID_status INTEGER NOT NULL," +
                "name TEXT NOT NULL," +
                "author TEXT NOT NULL," +
                "year INTEGER NOT NULL," +
                "number_pages INTEGER NOT NULL," +
                "chapter TEXT NOT NULL," +
                "genre TEXT NOT NULL," +
                "inventory_number TEXT NOT NULL," +
                "cipher TEXT NOT NULL," +
                "date_receiving TEXT," +
                "FOREIGN KEY (ID_library_card) REFERENCES LibraryCards(ID_library_card)," +
                "FOREIGN KEY (ID_status) REFERENCES Status(ID_status));");
        stmt.execute();
        stmt = con.prepareStatement("CREATE TABLE if not exists Penalties (" +
                "ID_penalty INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL CHECK(ID_penalty > 0)," +
                "ID_reader INTEGER NOT NULL CHECK(ID_reader > 0)," +
                "ID_status INTEGER NOT NULL," +
                "name TEXT NOT NULL," +
                "fine INTEGER CHECK(fine > 0)," +
                "FOREIGN KEY (ID_reader) REFERENCES Readers(ID_reader)," +
                "FOREIGN KEY (ID_status) REFERENCES Status(ID_status));");
        stmt.execute();
        stmt = con.prepareStatement("CREATE TABLE if not exists Notices (" +
                "ID_notice INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL CHECK(ID_notice > 0)," +
                "message TEXT NOT NULL," +
                "ID_penalty INTEGER NOT NULL CHECK(ID_penalty > 0)," +
                "FOREIGN KEY (ID_penalty) REFERENCES Penalties(ID_penalty));");
        stmt.execute();
        stmt = con.prepareStatement("INSERT INTO Status " +
                "(ID_status, " +
                "status_value) " +
                "VALUES (1, 'Читатель')");
        stmt.execute();
        stmt = con.prepareStatement("INSERT INTO Status " +
                "(ID_status, " +
                "status_value) " +
                "VALUES (2, 'Новый')");
        stmt.execute();
        stmt = con.prepareStatement("INSERT INTO Status " +
                "(ID_status, " +
                "status_value) " +
                "VALUES (3, 'Старый')");
        stmt.execute();
        stmt = con.prepareStatement("INSERT INTO Status " +
                "(ID_status, " +
                "status_value) " +
                "VALUES (4, 'Наличие')");
        stmt.execute();
        stmt = con.prepareStatement("INSERT INTO Status " +
                "(ID_status, " +
                "status_value) " +
                "VALUES (5, 'Отсутствие')");
        stmt.execute();
//        stmt = con.prepareStatement("INSERT INTO Readers " +
//                "(full_name, " +
//                "ID_status, " +
//                "phone_number, " +
//                "email, " +
//                "address, " +
//                "passport) " +
//                "VALUES ('Дереза',1,'фон','емал','адрес','пасп')");
//        stmt.execute();
        stmt.close();
        closeDB();
    }

    public int maxID_Readers() throws SQLException {
        int id = 0;
        try{
            initDB();
            stmt = con.prepareStatement("SELECT MAX(ID_reader) FROM Readers");
            rs = stmt.executeQuery();
            id = rs.getInt("MAX(ID_reader)") + 1;
            closeDB();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public int maxID_Penalties() throws SQLException {
        int id = 0;
        try{
            initDB();
            stmt = con.prepareStatement("SELECT MAX(ID_penalty) FROM Penalties");
            rs = stmt.executeQuery();
            id = rs.getInt("MAX(ID_penalty)") + 1;
            closeDB();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public int maxID_Notices() throws SQLException {
        int id = 0;
        try{
            initDB();
            stmt = con.prepareStatement("SELECT MAX(ID_notice) FROM Notices");
            rs = stmt.executeQuery();
            id = rs.getInt("MAX(ID_notice)") + 1;
            closeDB();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public void addReader(Reader reader) throws SQLException{
//        stmt = con.prepareStatement("SELECT MAX(ID_library_card) FROM LibraryCards");
//        rs = stmt.executeQuery();
//        int ID_library_card = rs.getInt("MAX(ID_library_card)");
        //int ID_library_card = reader.getID_library_card();
        String full_name = reader.getFull_name();
        int status = reader.getID_status();
        String phone_number = reader.getPhone_number();
        String email = reader.getEmail();
        String address = reader.getAddress();
        String passport = reader.getPassport();
        try{
            initDB();
            stmt = con.prepareStatement("INSERT INTO Readers " +
                    "(full_name, " +
                    "ID_status, " +
                    "phone_number, " +
                    "email, " +
                    "address, " +
                    "passport) " +
                    "VALUES (?,?,?,?,?,?)");
            stmt.setObject(1, full_name);
            stmt.setObject(3, status);
            stmt.setObject(4, phone_number);
            stmt.setObject(5, email);
            stmt.setObject(6, address);
            stmt.setObject(7, passport);
            stmt.execute();
            stmt.close();
            closeDB();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void addLibraryCard(LibraryCard libraryCard) throws SQLException{
//        stmt = con.prepareStatement("SELECT MAX(ID_library_card) FROM LibraryCards");
//        rs = stmt.executeQuery();
//        int ID_library_card = rs.getInt("MAX(ID_library_card)") + 1;
        stmt = con.prepareStatement("SELECT MAX(ID_reader) FROM Readers");
        rs = stmt.executeQuery();
        int ID_reader = rs.getInt("MAX(ID_reader)");
        try{
            initDB();
            stmt = con.prepareStatement("INSERT INTO Readers" +
                    "(ID_reader) " +
                    "VALUES (?)");
            //stmt.setObject(1, ID_library_card);
            stmt.setObject(1, ID_reader);
            stmt.execute();
            stmt.close();
            closeDB();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void updateReader(int ID_reader, int status) throws SQLException {
        try{
            initDB();
            stmt = con.prepareStatement("UPDATE Readers set status = ? WHERE ID_reader = ?");
            stmt.setObject(1, status);
            stmt.setObject(2, ID_reader);
            stmt.executeUpdate();
            stmt.close();
            closeDB();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void updatePenalty(int ID_penalty, int status) throws SQLException {
        try{
            initDB();
            stmt = con.prepareStatement("UPDATE Penalties set status = ? WHERE ID_penalty = ?");
            stmt.setObject(1, status);
            stmt.setObject(2, ID_penalty);
            stmt.executeUpdate();
            stmt.close();
            closeDB();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Reader getReader(int id) throws SQLException{
        Reader reader = null;
        try{
            initDB();
            stmt = con.prepareStatement("SELECT * FROM Readers WHERE ID_reader = ?");
            stmt.setObject(1, id);
            rs = stmt.executeQuery();
            while(rs.next()){
                reader = new Reader(
//                        rs.getInt("ID_reader"),
                        //rs.getInt("ID_library_card"),
                        rs.getString("full_name"),
                        rs.getInt("ID_status"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("passport"));
            }
            stmt.close();
            closeDB();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return reader;
    }

    /*public Notice getNotice(int id) throws SQLException{
        Notice notice = null;
        try{
            initDB();
            stmt = con.prepareStatement("SELECT * FROM Notices WHERE ID_notice = ?");
            stmt.setObject(1, id);
            rs = stmt.executeQuery();
            while(rs.next()){
                notice = new Notice(
                        rs.getInt("ID_notice"),
                        rs.getString("message"),
                        rs.getInt("ID_penalty"));
            }
            stmt.close();
            closeDB();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return notice;
    }*/

    public ArrayList<Reader> getReaders() throws SQLException{
        ArrayList<Reader> readers = new ArrayList<>();
        try{
            initDB();
            stmt = con.prepareStatement("SELECT ID_library_card, full_name, ID_status, phone_number, email, address, passport FROM Readers");
            rs = stmt.executeQuery();
            while (rs.next()) {
                readers.add(new Reader(
                    rs.getInt("ID_library_card"),
                    rs.getString("full_name"),
                    rs.getInt("ID_status"),
                    rs.getString("phone_number"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getString("passport")));
            }
            closeDB();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return readers;
    }

    public int getReadersSize() throws SQLException{
        int size = 0;
        try{
            initDB();
            stmt = con.prepareStatement("SELECT COUNT(*) FROM Readers");
            rs = stmt.executeQuery();
            size = rs.getInt("COUNT(*)");
            closeDB();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return size;
    }

    public ArrayList<Reader> getDebtors() throws SQLException{
        ArrayList<Reader> debtors = new ArrayList<>();
        try{
            initDB();
            stmt = con.prepareStatement("SELECT ID_library_card, full_name, ID_status, phone_number, email, address, passport FROM Readers WHERE ID_status != 1");
            rs = stmt.executeQuery();
            while (rs.next()) {
                debtors.add(new Reader(
                        rs.getInt("ID_library_card"),
                        rs.getString("full_name"),
                        rs.getInt("ID_status"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("passport")));
            }
            closeDB();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return debtors;
    }

    public int getDebtorsSize() throws SQLException{
        int size = 0;
        try{
            initDB();
            stmt = con.prepareStatement("SELECT COUNT(*) FROM Readers WHERE ID_status != 1");
            rs = stmt.executeQuery();
            size = rs.getInt("COUNT(*)");
            closeDB();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return size;
    }

    public ArrayList<Penalty> getPenalties() throws SQLException{
        ArrayList<Penalty> penalties = new ArrayList<>();
        try{
            initDB();
            //stmt = con.prepareStatement("SELECT ID_penalty, ID_reader, ID_status, name, fine FROM Penalties");
            stmt = con.prepareStatement("SELECT [ID penalty], [Library card], [Name penalty], [Fine penalty] FROM ReadersPenaltyNotice");
            rs = stmt.executeQuery();
            while (rs.next()) {
                penalties.add(new Penalty(
                    rs.getInt("ID penalty"),
                    rs.getInt("Library card"),
                    rs.getString("Name penalty"),
                    rs.getDouble("Fine penalty")));
            }
            closeDB();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return penalties;
    }

    public int getPenaltiesSize() throws SQLException{
        int size = 0;
        try{
            initDB();
            stmt = con.prepareStatement("SELECT COUNT(*) FROM ReadersPenaltyNotice");
            rs = stmt.executeQuery();
            size = rs.getInt("COUNT(*)");
            closeDB();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return size;
    }

    public ArrayList<Notice> getNotices() throws SQLException{
        ArrayList<Notice> notices = new ArrayList<>();
        try{
            initDB();
            stmt = con.prepareStatement("SELECT [ID notice], [Library card], [Message] FROM ReadersPenaltyNotice");
            rs = stmt.executeQuery();
            while (rs.next()) {
                notices.add(new Notice(
                    rs.getInt("ID notice"),
                    rs.getInt("Library card"),
                    rs.getString("Message")));
            }
            closeDB();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return notices;
    }

    public int getNoticesSize() throws SQLException{
        int size = 0;
        try{
            initDB();
            stmt = con.prepareStatement("SELECT COUNT(*) FROM ReadersPenaltyNotice");
            rs = stmt.executeQuery();
            size = rs.getInt("COUNT(*)");
            closeDB();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return size;
    }

    public void closeDB() throws SQLException {
        con.close();
    }
}
