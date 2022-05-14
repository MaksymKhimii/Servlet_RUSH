package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManagerDB {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "Maks_Khimii";
    private static final String PASSWORD = "makskhimiy24112003";

    private static ManagerDB instance;

    // Singleton
    private ManagerDB() {}

    synchronized public static ManagerDB getInstance(){
        if(instance==null){
           instance = new ManagerDB();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return con;
    }
}
