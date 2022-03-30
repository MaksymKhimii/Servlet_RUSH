package db;

import db.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class ProductsDao {
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
    //проверка, есть ли в базе продукт с таким названием
    public static boolean validateProduct(String name) {

        boolean status=false;
        try{
            String GETUSER="select * from mydbtest.products where name=?";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps=con.prepareStatement(GETUSER);
            ps.setString(1,name);
            try (ResultSet rs=ps.executeQuery()){
                status=rs.next();
            }
        }catch(SQLException | ClassNotFoundException e){e.printStackTrace();}
        return status;
    }

    public static boolean validateProduct(String name, int quantity, double weight, boolean tonnage, double price) {
        boolean answer = false;
        try{
            String GETUSER="select * from mydbtest.products where name=? AND quantity=? AND weight=? AND tonnage=? AND price=?;";
            //приводим boolean к int чтобы запрос работал
            int tonnageInt=Product.boolToInt(tonnage);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps=con.prepareStatement(GETUSER);
            ps.setString(1,name);
            ps.setString(2, String.valueOf(quantity));
            ps.setString(3, String.valueOf(weight));
            ps.setString(4, String.valueOf(tonnageInt));
            ps.setString(5, String.valueOf(price));
            try (ResultSet rs=ps.executeQuery()){
                answer=rs.next();
            }
        }catch(SQLException | ClassNotFoundException e){e.printStackTrace();}
        return answer;
    }

    //добавление нового продукта в таблицу products
    public static Boolean addProduct(String name, int quantity, double weight, boolean tonnage, double price) throws SQLException, ClassNotFoundException {
        boolean status= false;
        //приводим boolean к int чтобы запрос работал
        int tonnageInt=Product.boolToInt(tonnage);
        try {
            String ADD_Product="INSERT INTO mydbtest.products(name,quantity,weight,tonnage, price) VALUES(?,?,?,?,?);";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement ps=con.prepareStatement(ADD_Product);
            ps.setString(1,name);
            ps.setString(2, String.valueOf(quantity));
            ps.setString(3, String.valueOf(weight));
            ps.setString(4, String.valueOf(tonnageInt));
            ps.setString(5, String.valueOf(price));
            ps.executeUpdate();
            if(ProductsDao.validateProduct(name, quantity, weight, tonnage, price)){
                status=true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }
    //изменяет поля продукта с определенным name
    public static void changeProduct(String name, int quantity, double weight, boolean tonnage, double price) {
        String Query = "UPDATE mydbtest.products SET quantity=?, weight=?, tonnage=?, price=? WHERE name =?;";
        //приводим boolean к int чтобы запрос работал
        int tonnageInt=Product.boolToInt(tonnage);
          try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(Query);
            pstmt.setString(1, String.valueOf(quantity));
            pstmt.setString(2, String.valueOf(weight));
            pstmt.setString(3, String.valueOf(tonnageInt));
            pstmt.setString(4, String.valueOf(price));
            pstmt.setString(5, name);
            int rs = pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
      }
    public static boolean deleteProduct(String name) throws SQLException, ClassNotFoundException {
        boolean answer = false;
        try {
            String DeleteProduct="DELETE FROM mydbtest.products where name =?;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement ps=con.prepareStatement(DeleteProduct);
            ps.setString(1,name);
            ps.executeUpdate();
            if(ProductsDao.validateProduct(name)){
                answer=false;
            } else {
                answer=true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return answer;
    }
    public static ArrayList<Product> getAllProducts(){
        ArrayList<Product> products= new ArrayList<Product>(){};
        String Query = "SELECT idproducts, name, quantity, weight, tonnage, price FROM mydbtest.products;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(Query);
            ResultSet rs=pstmt.executeQuery();
            boolean found= false;
            while (rs.next()){
                Product AllProducts = new Product();
                AllProducts.setIdproducts(rs.getInt("idproducts"));
                AllProducts.setName(rs.getString("name"));
                AllProducts.setQuantity(rs.getInt("quantity"));
                AllProducts.setWeight(rs.getDouble("weight"));
                AllProducts.setTonnage(rs.getBoolean("tonnage"));
                AllProducts.setPrice(rs.getDouble("price"));
                products.add(AllProducts);
                found= true;
            }
            rs.close(); pstmt.close();
            if(found){return products;}
            else {return null;}
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();}
        return products;
    }
    public static ArrayList<Product> getOneProduct(String name){
        ArrayList<Product> products= new ArrayList<Product>(){};
        String Query = "SELECT idproducts, name, quantity, weight, tonnage, price FROM mydbtest.products WHERE name=?;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(Query);
            pstmt.setString(1, name);
            ResultSet rs=pstmt.executeQuery();
            boolean found= false;
            while (rs.next()){
                Product AllProducts = new Product();
                AllProducts.setIdproducts(rs.getInt("idproducts"));
                AllProducts.setName(rs.getString("name"));
                AllProducts.setQuantity(rs.getInt("quantity"));
                AllProducts.setWeight(rs.getDouble("weight"));
                AllProducts.setTonnage(rs.getBoolean("tonnage"));
                AllProducts.setPrice(rs.getDouble("price"));
                products.add(AllProducts);
                found= true;
            }
            rs.close(); pstmt.close();
            if(found){return products;}
            else {return null;}
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();}
        return products;
    }
}
