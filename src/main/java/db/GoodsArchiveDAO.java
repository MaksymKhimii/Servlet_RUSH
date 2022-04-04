package db;

import java.sql.*;

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
    /**переносит данные чека из basket в goodsarchive для сохранения*/
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

    /**удаляем продукты чека по его idreceipt*/
    public static boolean deleteReceiptFromArchive(int idreceipt) throws SQLException, ClassNotFoundException {
        boolean answer = false;
        try {
            String QUERY="DELETE FROM mydbtest.goodsarchive WHERE idreceipt =?;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement ps=con.prepareStatement(QUERY);
            ps.setString(1, String.valueOf(idreceipt));
            ps.executeUpdate();
            if(!GoodsArchiveDAO.validateReceipt(idreceipt)){
                answer=true;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return answer;
    }

    /**удаляем продукты чека по его idreceipt*/
    public static void deleteProdFromReceipt(int idproduct, int idreceipt) throws SQLException, ClassNotFoundException {
        boolean answer = false;
        try {




            String QUERY="DELETE FROM mydbtest.goodsarchive WHERE idproduct=? AND idreceipt=?;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement ps=con.prepareStatement(QUERY);
            ps.setString(1, String.valueOf(idproduct));
            ps.setString(2, String.valueOf(idreceipt));
            ps.executeUpdate();
                answer=true;
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    /** проверка, есть ли чек с указаным idreceipt в архиве*/
    public static boolean validateReceipt(int idreceipt) {

        boolean status=false;
        try{
            String GETUSER="SELECT * FROM mydbtest.goodsarchive WHERE idreceipt=?;";

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

    /** проверка, есть ли продукт с указаным idproduct в чеке idreceipt*/
    public static boolean validateProdInReceipt(int idproduct, int idreceipt) {

        boolean status=false;
        try{
            String GETUSER="SELECT * FROM mydbtest.goodsarchive WHERE idproduct=? AND idreceipt=?;";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps=con.prepareStatement(GETUSER);
            ps.setString(1, String.valueOf(idproduct));
            ps.setString(2, String.valueOf(idreceipt));

            try (ResultSet rs=ps.executeQuery()){
                status=rs.next();
            }
        }catch(SQLException | ClassNotFoundException e){e.printStackTrace();}
        return status;
    }

    /**считает сумму продукта который будет удален из чека*/
    public static double getCostOneProduct(int idreceipt, int idproduct){
        int quantity=0;
        boolean tonnage=false;
        double weight=0;
        double price =0;
        try{
            String GETUSER="SELECT quantity, weight, tonnage, price FROM mydbtest.goodsarchive WHERE idreceipt=? AND idproduct=?;";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps=con.prepareStatement(GETUSER);
            ps.setString(1, String.valueOf(idreceipt));
            ps.setString(2, String.valueOf(idproduct));
            try (ResultSet rs=ps.executeQuery()){
                while (rs.next()){
                    quantity=rs.getInt("quantity");
                    weight=rs.getDouble("weight");
                    tonnage= rs.getBoolean("tonnage");
                    price=rs.getDouble("price");
                }
            }
        }catch(SQLException | ClassNotFoundException e){e.printStackTrace();}

        double cost=0;
        if(tonnage){
            cost=weight*price;
        }else {
            cost=quantity*price;
        }
        return cost;
    }

    /**обновляет сумму в таблице goodsarchive */
    public static void updateSum(int idreceipt){
        double totalSum=0;
        try {
            String Query1 = "SELECT total_sum FROM mydbtest.receipts WHERE idreceipt=?;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps= con.prepareStatement(Query1);
            ps.setString(1, Integer.toString(idreceipt));
            ResultSet res=ps.executeQuery();
            while (res.next()){
                totalSum=res.getDouble("total_sum");
            }

            String Query2 = "UPDATE mydbtest.goodsarchive SET total_sum=? WHERE idreceipt=?;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps2= con2.prepareStatement(Query2);
            ps2.setString(1, Double.toString(totalSum));
            ps2.setString(2, Integer.toString(idreceipt));
            ps.executeQuery();
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
