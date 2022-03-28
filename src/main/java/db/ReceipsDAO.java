package db;

import db.entity.Product;

import java.sql.*;
import java.util.ArrayList;

public class ReceipsDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "Maks_Khimii";
    private static final String PASSWORD = "makskhimiy24112003";
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/epam?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true", "root", "1234567h");
        con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        // con.setAutoCommit(true);

        return con;
    }

    /** проверка, есть ли чек с указаным idreceipt в бд*/
    public static boolean validateReceipt(int idreceipt) {

        boolean status=false;
        try{
            String GETUSER="select * from mydbtest.receipts where idreceipt=?";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps=con.prepareStatement(GETUSER);
            ps.setString(1, String.valueOf(idreceipt));
            try (ResultSet rs=ps.executeQuery()){
                status=rs.next();
            }
        }catch(SQLException | ClassNotFoundException e){e.printStackTrace();}
        return status;
    }
    /** создание нового чека*/
    public static boolean addReceipt(String cashier_name) throws SQLException, ClassNotFoundException {

        boolean status=false;
        try {
            String ADD_Product = "INSERT INTO mydbtest.receipts(cashier_name) VALUES(?);";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement ps = con.prepareStatement(ADD_Product);
            ps.setString(1, cashier_name);
            ps.executeUpdate();
            status=true;
          /*  PreparedStatement ps2=con.prepareStatement(Get_ID);
            ResultSet rs2=ps2.executeQuery();
            while(rs2.next()) {
                receiptId = rs2.getInt("idreceipt");
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
    /**этот метод достает id последнего созданого чека*/
    public static int getLastReceiptId() throws ClassNotFoundException, SQLException {
        int idreceipt=0;
        try {
            String Get_ID = "SELECT idreceipt FROM mydbtest.receipts ORDER BY idreceipt DESC LIMIT 1;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps= con.prepareStatement(Get_ID);
            ResultSet res=ps.executeQuery();
            while (res.next()){
               idreceipt=res.getInt("idreceipt");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return idreceipt;
    }

    /**тут мы закрываем чек, но нужно будет сделать очистку таблицы basket*/
    public static boolean  closeReceipt(double total_sum) throws SQLException, ClassNotFoundException {

        boolean status = false;
        try {
            String ADD_Product = "INSERT INTO mydbtest.receipts(total_sum) VALUES(?);";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement ps = con.prepareStatement(ADD_Product);
            ps.setString(1, String.valueOf(total_sum));
            ps.executeUpdate();
            status = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
