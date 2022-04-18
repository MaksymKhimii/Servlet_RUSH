package db;

import db.entity.Basket;
import db.entity.Product;

import java.sql.*;
import java.util.ArrayList;

/** RU: слой ДАО для взаимодействия программы с таблицей basket в базе дынных,
 *      которая хранит продукты во время создания ного чека
 * ENG: DAO layer for program interaction with the basket table in the database,
 *      which stores products during the creation of a receipt*/

public class BasketDAO {
    /**
     *  RU: переменные для подключения к базе данных
     * ENG: database connection variables
     */
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "Maks_Khimii";
    private static final String PASSWORD = "makskhimiy24112003";


    /**
     * RU: метод для создания соединения между базой данных и программой
     * ENG: method to create connection between database and program
     * @return Connection
     */
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/epam?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true", "root", "1234567h");
        con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return con;
    }

    /**
     * RU: метод проверяет добавлен ли продукт с определенными параметрами
     * ENG: the method checks if a product has been added with certain parameters
     * @param idproduct product id
     * @param name product name
     * @param quantity quantity of product type
     * @param weight product weight
     * @param tonnage whether the item is by weight
     * @param price price
     * @return boolean
     */
    public static boolean validateBasket( int idproduct, String name, int quantity, double weight,
                                          boolean tonnage, double price) throws SQLException {
        boolean answer = false;
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

    /** RU: проверка корзины на налачие хотя бы одного продукта
     * ENG: checking the basket for at least one product
     * @return boolean
     */
    public static boolean checkBasket() {
        boolean answer = false;
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

    /** RU: метод для добавления в корзину последнего созданного чека
     * ENG: method for adding the last created receipt to the basket
     * @param idproduct product id
     * @param name product name
     * @param quantity quantity of product type
     * @param weight product weight
     * @param tonnage whether the item is by weight
     * @param price price
     * @return boolean
     */
    public static boolean addProdToBasket(int idproduct, String name, int quantity, double weight,
                                          boolean tonnage, double price) throws SQLException, ClassNotFoundException {
        boolean status = false;
        int idreceipt = ReceiptsDAO.getLastReceiptId();
        int tonnageInt = Product.boolToInt(tonnage);
        ProductsDAO.updateCountProduct(name, quantity, weight, tonnage);
        if(tonnage){
            if(ProductsDAO.validateWeight(name, weight)){
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
            if(ProductsDAO.validateQuantity(name, quantity)){
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

    /** RU: удаляет все продукты из basket которые пренадлежат к открытому чеку
     * ENG: removes all products from the basket that belong to the open receipt
     * @param idreceipt product id
     */
    public static void deleteBasket(int idreceipt) throws SQLException {
        try {
            String GETUSER = "DELETE FROM mydbtest.basket WHERE idreceipt=?;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = con.prepareStatement(GETUSER);
            ps.setString(1, String.valueOf(idreceipt));
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** RU: метод получает суму за один продукт
     *       (метод будет использоваться при добавлении продукта в корзину
     *       для следующего изменения отображаеого значения общей суммы )
     * ENG: the method receives the amount for one product
     *       (the method will be used when adding the product to the cart
     *       for the next change in the displayed value of the total amount)
     * @param idproduct product id
     * @param name product name
     * @param quantity quantity of product type
     * @param weight product weight
     * @param tonnage whether the item is by weight
     * @param price price
     * @return double
     */
    public static double countSumOneProduct( int idproduct, String name, int quantity, double weight,
                                          boolean tonnage, double price) {
       double SumOneProd;
        if(tonnage){
            SumOneProd=weight*price;
        } else {
            SumOneProd=quantity*price;
        }
        return SumOneProd;
    }

    /** RU: метод для получения данных всех продуктов чека
     * ENG: method for obtaining data of all receipt products
     * @return ArrayList<Basket>
     */
    public static ArrayList<Basket> getAllBasket(){
        ArrayList<Basket> basket= new ArrayList<>() {
        };
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
