package db;

import db.entity.Basket;
import db.entity.Product;

import java.sql.*;
import java.util.ArrayList;

public class GoodsArchiveDAO {
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

    public static void saveBacket(int idreceipt) throws SQLException, ClassNotFoundException {

        //приводим boolean к int чтобы запрос работал

        //если это весовой продукт, проверяем валидный ли его вес
        // если это не весовой продук, проверяем валидность его колличества
        double total_sum=ReceipsDAO.getReceiptSum(idreceipt);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String QUERY1 = "INSERT INTO mydbtest.goodsarchive(idreceipt, idproduct, name, quantity, weight, tonnage, price)" +
                    " SELECT idreceipt, idproduct, name, quantity, weight, tonnage, price FROM mydbtest.basket WHERE idreceipt=?;";
            PreparedStatement ps1=con.prepareStatement(QUERY1);
            ps1.setString(1, String.valueOf(idreceipt));
            ps1.executeUpdate();

            String QUERY2 = "UPDATE  mydbtest.goodsarchive SET total_sum=? WHERE idreceipt=?;";
            PreparedStatement ps2 = con.prepareStatement(QUERY2);
            ps2.setString(1, String.valueOf(total_sum));
            ps2.setString(2, String.valueOf(idreceipt));
            ps2.executeUpdate();
            //после переноса данных удаляем все из basket
            BasketDAO.deleteBasket(idreceipt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
