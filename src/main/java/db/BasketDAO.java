package db;

import db.entity.Basket;
import db.entity.Product;

import java.sql.*;
import java.util.ArrayList;

public class BasketDAO {
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

    /**
     * мотод проверяет добавлен ли продукт с определенными параметра
     * <p>
     * ми
     */
    public static boolean validateBasket( int idproduct, String name, int quantity, double weight,
                                          boolean tonnage, double price) throws SQLException, ClassNotFoundException {
        boolean answer = false;
        int idreceipt = ReceipsDAO.getLastReceiptId();
        //приводим boolean к int чтобы запрос работал
        int tonnageInt = Product.boolToInt(tonnage);
        try {
            String GETUSER = "SELECT * FROM mydbtest.basket where idproduct=? AND name=? AND" +
                    " quantity=? AND weight=? AND tonnage=? AND price=?;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = con.prepareStatement(GETUSER);
            ps.setString(1, String.valueOf(idproduct));
            ps.setString(2, String.valueOf(name));
            ps.setString(3, String.valueOf(quantity));
            ps.setString(4, String.valueOf(weight));
            ps.setString(5, String.valueOf(tonnageInt));
            ps.setString(6, String.valueOf(price));
            try (ResultSet rs = ps.executeQuery()) {
                answer = rs.next();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return answer;
    }
    /**проверка корзины на налачие хотя бы одного продукта*/
    public static boolean checkBasket() throws SQLException, ClassNotFoundException {
        boolean answer = false;
        int idreceipt = ReceipsDAO.getLastReceiptId();
        //приводим boolean к int чтобы запрос работал
        try {
            String QUERY = "SELECT * FROM mydbtest.basket;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = con.prepareStatement(QUERY);
            try (ResultSet rs = ps.executeQuery()) {
                answer = rs.next();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return answer;
    }

    /**метод для добавления в корзину последнего созданного чека */
    public static boolean addProdToBasket(int idproduct, String name, int quantity, double weight,
                                          boolean tonnage, double price) throws SQLException, ClassNotFoundException {
        boolean status = false;
        int idreceipt = ReceipsDAO.getLastReceiptId();
        //приводим boolean к int чтобы запрос работал
        int tonnageInt = Product.boolToInt(tonnage);
        ProductsDao.updateCountProduct(name, quantity, weight, tonnage);
        //если это весовой продукт, проверяем валидный ли его вес
        // если это не весовой продук, проверяем валидность его колличества
        if(tonnage){
            if(ProductsDao.validateWeight(name, weight)){
                try {

                    String ADD_Product = "INSERT INTO mydbtest.basket (idreceipt, idproduct, name, quantity,weight,tonnage, price)" +
                            " VALUES(?,?,?,?,?,?,?);";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                    PreparedStatement ps = con.prepareStatement(ADD_Product);
                    ps.setString(1, String.valueOf(idreceipt));
                    ps.setString(2, String.valueOf(idproduct));
                    ps.setString(3, String.valueOf(name));
                    ps.setString(4, String.valueOf(quantity));
                    ps.setString(5, String.valueOf(weight));
                    ps.setString(6, String.valueOf(tonnageInt));
                    ps.setString(7, String.valueOf(price));
                    ps.executeUpdate();
                    if (BasketDAO.validateBasket(idproduct, name, quantity, weight, tonnage, price)) {
                        status = true;
                        return status;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if(ProductsDao.validateQuantity(name, quantity)){
                try {

                    String ADD_Product = "INSERT INTO mydbtest.basket (idreceipt, idproduct, name, quantity,weight,tonnage, price)" +
                            " VALUES(?,?,?,?,?,?,?);";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                    PreparedStatement ps = con.prepareStatement(ADD_Product);
                    ps.setString(1, String.valueOf(idreceipt));
                    ps.setString(2, String.valueOf(idproduct));
                    ps.setString(3, String.valueOf(name));
                    ps.setString(4, String.valueOf(quantity));
                    ps.setString(5, String.valueOf(weight));
                    ps.setString(6, String.valueOf(tonnageInt));
                    ps.setString(7, String.valueOf(price));
                    ps.executeUpdate();
                    if (BasketDAO.validateBasket(idproduct, name, quantity, weight, tonnage, price)) {
                        status = true;
                        return status;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return status;
    }
    /**удаляет все продукты из basket которые пренадлежат к открытому чеку*/
    public static boolean deleteBasket(int idreceipt) throws SQLException, ClassNotFoundException {
        boolean answer = false;
        try {
            String GETUSER = "DELETE FROM mydbtest.basket WHERE idreceipt=?;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = con.prepareStatement(GETUSER);
            ps.setString(1, String.valueOf(idreceipt));
            ps.executeUpdate();
                answer=true;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return answer;
    }
    /**метод получает суму за один продукт
     * (метод будет использоваться при добавлении продукта в корзину
     * для следующего изменения отображаеого значения общей суммы )*/
    public static double countSumOneProduct( int idproduct, String name, int quantity, double weight,
                                          boolean tonnage, double price) throws SQLException, ClassNotFoundException {
       double SumOneProd;
        int idreceipt = ReceipsDAO.getLastReceiptId();
        if(tonnage){
            SumOneProd=weight*price;
        } else {
            SumOneProd=quantity*price;
        }
        return SumOneProd;
    }

    /** метод для получения данных всех продуктов чека*/
    public static ArrayList<Basket> getAllBasket(){
        ArrayList<Basket> basket= new ArrayList<Basket>(){};
        String Query = "SELECT idreceipt, idproduct, name, quantity, weight, tonnage, price FROM mydbtest.basket;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(Query);
            ResultSet rs=pstmt.executeQuery();
            boolean found= false;
            while (rs.next()){
                Basket AllBasket = new Basket();
                AllBasket.setIdreceipt(rs.getInt("idreceipt"));
                AllBasket.setIdproduct(rs.getInt("idproduct"));
                AllBasket.setName(rs.getString("name"));
                AllBasket.setQuantity(rs.getInt("quantity"));
                AllBasket.setWeight(rs.getDouble("weight"));
                AllBasket.setTonnage(rs.getBoolean("tonnage"));
                AllBasket.setPrice(rs.getDouble("price"));
                basket.add(AllBasket);
                found= true;
            }
            rs.close(); pstmt.close();
            if(found){return basket;}
            else {return null;}
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();}
        return basket;
    }
}
