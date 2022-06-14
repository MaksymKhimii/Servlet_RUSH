package db;

import db.entity.Product;

import java.sql.*;
import java.util.ArrayList;

/** RU: слой ДАО для взаимодействия программы с таблицей products в базе дынных,
 *      которая хранит данные продуктов
 * ENG: DAO layer for program interaction with the basket table in the database,
 *      which stores products during the creation of a receipt*/
public class ProductsDAO {
    /** RU: метод для проверки, есть ли в базе товар с таким названием
     * (используется при добавлении нового продукта для избежания дубликатов)
     * ENG: method for checking if there is a product with the same name in the database
     * (used when adding a new product to avoid duplicates)
     * @param name name of product
     * @return status(validation)
     */
    public static boolean validateProduct(String name) {
        boolean status=false;
        try{
            String GETUSER="SELECT * FROM mydbtest.products WHERE name=?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps=con.prepareStatement(GETUSER);
            ps.setString(1,name);
            try (ResultSet rs=ps.executeQuery()){
                status=rs.next();
            }
        }catch(SQLException e){e.printStackTrace();}
        return status;
    }


    /** RU: метод проверки, существует ли товар с указаными параметрами
     * (используется как проверка при добавлении/изменении данных о товаре)
     * ENG: method to check if a product exists with the specified parameters
     * (used as a check when adding/changing product data)
     * @param name name of product
     * @param quantity quantity of product
     * @param weight weight of this product
     * @param tonnage  whether the item is by weight
     * @param price price of this product
     * @return status(validation)
     */
    public static boolean validateProduct(String name, int quantity, double weight, boolean tonnage, double price) {
        boolean answer = false;
        try{
            String GETUSER="SELECT * FROM mydbtest.products WHERE name=? AND quantity=? AND weight=? AND tonnage=? AND price=?;";
            int tonnageInt=Product.boolToInt(tonnage);
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps=con.prepareStatement(GETUSER);
            ps.setString(1,name);
            ps.setString(2, String.valueOf(quantity));
            ps.setString(3, String.valueOf(weight));
            ps.setString(4, String.valueOf(tonnageInt));
            ps.setString(5, String.valueOf(price));
            try (ResultSet rs=ps.executeQuery()){
                answer=rs.next();
            }
        }catch(SQLException e){e.printStackTrace();}
        return answer;
    }


    /** RU: метод добавление нового товара(продукта) в таблицу products
     * ENG: method for adding a new item (product) to the products table
     * @param name name of product
     * @param quantity quantity of product
     * @param weight weight of this product
     * @param tonnage  whether the item is by weight
     * @param price price of this product
     * @return adding status
     */
    public static boolean addProduct(String name, int quantity, double weight, boolean tonnage, double price) {
        boolean status= false;
        int tonnageInt=Product.boolToInt(tonnage);
        try {
            String ADD_Product="INSERT INTO mydbtest.products(name,quantity,weight,tonnage, price) VALUES(?,?,?,?,?);";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps=con.prepareStatement(ADD_Product);
            ps.setString(1,name);
            ps.setString(2, String.valueOf(quantity));
            ps.setString(3, String.valueOf(weight));
            ps.setString(4, String.valueOf(tonnageInt));
            ps.setString(5, String.valueOf(price));
            ps.executeUpdate();
            if(ProductsDAO.validateProduct(name, quantity, weight, tonnage, price)){
                status=true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }


    /** RU: метод для изменения поля товара(продукта) с определенным name
     * ENG: method for changing the product (product) field with a specific name
     * @param name name of product
     * @param quantity quantity of product
     * @param weight weight of this product
     * @param tonnage  whether the item is by weight
     * @param price price of this product
     */
    public static void changeProduct(String name, int quantity, double weight, boolean tonnage, double price) {
        String Query = "UPDATE mydbtest.products SET quantity=?, weight=?, tonnage=?, price=? WHERE name =?;";
        int tonnageInt=Product.boolToInt(tonnage);
          try {
              Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(Query);
            pstmt.setString(1, String.valueOf(quantity));
            pstmt.setString(2, String.valueOf(weight));
            pstmt.setString(3, String.valueOf(tonnageInt));
            pstmt.setString(4, String.valueOf(price));
            pstmt.setString(5, name);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
      }


    /** RU: метод удаления товара(продукта)
     * ENG: item(product) deletion method
     * @param name name of product
     * @return deletion status
     */
    public static boolean deleteProduct(String name) throws SQLException {
        boolean answer = false;
        try {
            String DeleteProduct="DELETE FROM mydbtest.products WHERE name =?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps=con.prepareStatement(DeleteProduct);
            ps.setString(1,name);
            ps.executeUpdate();
            if(!ProductsDAO.validateProduct(name)){
                answer=true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return answer;
    }


    /** RU: метод получает данные всех продуктов(товаров)
     * ENG: the method receives the data of all products (products)
     * @return All information about Products
     */
    public static ArrayList<Product> getAllProducts(){
        ArrayList<Product> products= new ArrayList<>(){};
        String Query = "SELECT idproducts, name, quantity, weight, tonnage, price FROM mydbtest.products;";
        try {
            Connection con = ManagerDB.getInstance().getConnection();
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
        } catch (SQLException e) {
            e.printStackTrace();}
        return products;
    }


    /** RU: метод получает информацию об одном продукте(товаре)
     * ENG: the method receives information about one product (goods)
     * @param name name of product
     * @return All information about this Product
     */
    public static ArrayList<Product> getOneProduct(String name){
        ArrayList<Product> products= new ArrayList<Product>(){};
        String Query = "SELECT idproducts, name, quantity, weight, tonnage, price FROM mydbtest.products WHERE name=?;";
        try {
            Connection con = ManagerDB.getInstance().getConnection();
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
        } catch (SQLException e) {
            e.printStackTrace();}
        return products;
    }


    /** RU: метод для валидации колличества(поле quantity) товара(продукта)
     * ENG: method for validating the quantity (quantity field) of a product (product)
     * @param name name of product
     * @param requiredQuantity the required amount to add to the check
     * @return status(validation)
     */
    public static boolean validateQuantity(String name, int requiredQuantity) {
        //requiredQuantity - the required quantity to add to the receipt,
        // which is subtracted when added to the receipt
        boolean status=false;
        try{
            String GETUSER="SELECT quantity FROM mydbtest.products WHERE name=?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps=con.prepareStatement(GETUSER);
            ps.setString(1,name);
            try (ResultSet rs=ps.executeQuery()) {
                int actualQuantity = 0;
                while (rs.next()) {
                    actualQuantity = rs.getInt("quantity");
                }
                status= requiredQuantity <= actualQuantity;
            }
        }catch(SQLException e){e.printStackTrace();}
        return status;
    }


    /** RU: метод для валидации веса(поле weight) товара(продукта)
     * ENG: method for weight validation (weight field) of a product (product)
     * @param name name of product
     * @param requiredWeight required weight (in kg) to be added to the receipt
     * @return status(validation)
     */
    public static boolean validateWeight(String name, double requiredWeight) {
        //requiredWeight - the required weight (in kg) to be added to the receipt,
        // which is subtracted when added to the check
        boolean status=false;
        try{
            String GETUSER="SELECT weight FROM mydbtest.products WHERE name=?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps=con.prepareStatement(GETUSER);
            ps.setString(1,name);
            try (ResultSet rs=ps.executeQuery()) {
                double actualWeight = 0;
                while (rs.next()) {
                    actualWeight = rs.getDouble("weight");
                }
                status= requiredWeight <= actualWeight;
            }
        }catch(SQLException e){e.printStackTrace();}
        return status;
    }


    /** RU: метод возвращает ответ на вопрос: весовой ли это продукт?
     * ENG:the method returns the answer to the question: is this a product by weight?
     * @param name name of product
     * @return status(validation)
     */
    public static boolean validateTonnage(String name) {
        boolean status=false;
        try{
            String GETUSER="SELECT tonnage FROM mydbtest.products WHERE name=?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps=con.prepareStatement(GETUSER);
            ps.setString(1,name);
            try (ResultSet rs=ps.executeQuery()) {
                boolean tonnage = false;
                while (rs.next()) {
                    tonnage = rs.getBoolean("tonnage");
                }
                status= tonnage;
            }
        }catch(SQLException e){e.printStackTrace();}
        return status;
    }


    /** RU: метод для изменения поля товара(продукта) с определенным name в таблице products
     *      после добавления определенного колличества(штук или кг) в корзину
     *      Примечание: параметры, которые принимает метод это колличество
     *      едениц/веса которое добавляется в таблицу basket
     * ENG: method for changing the field of a product (product) with a specific name in the products table
     *      after adding a certain amount (pieces or kg) to the cart
     *      Note: the parameters that the method accepts are the number
     *      units/weight that is added to the basket table
     * @param name name of product
     * @param quantity quantity of product
     * @param weight weight of product
     * @param tonnage whether the item is by weight
     */
    public static void updateCountProduct(String name, int quantity, double weight, boolean tonnage) {
        if(tonnage){
            String Query1 = "SELECT weight FROM mydbtest.products WHERE name=?;";
            double oldWeight=0;
            try {
                Connection con = ManagerDB.getInstance().getConnection();
                PreparedStatement pstmt = con.prepareStatement(Query1);
                pstmt.setString(1, name);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()){
                    oldWeight=rs.getDouble("weight");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            double newWeight=oldWeight-weight;

            String Query2 = "UPDATE mydbtest.products SET weight=? WHERE name =?;";
            try {
                Connection con = ManagerDB.getInstance().getConnection();
                PreparedStatement pstmt = con.prepareStatement(Query2);
                pstmt.setString(1, String.valueOf(newWeight));
                pstmt.setString(2, name);
                pstmt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else{
            String Query3 = "SELECT quantity FROM mydbtest.products WHERE name=?;";
            int oldQuantity=0;
            try {
                Connection con = ManagerDB.getInstance().getConnection();
                PreparedStatement pstmt = con.prepareStatement(Query3);
                pstmt.setString(1, name);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()){
                    oldQuantity=rs.getInt("quantity");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            double newQuantity=oldQuantity-quantity;
            String Query4 = "UPDATE mydbtest.products SET quantity=? WHERE name =?;";
            try {
                Connection con = ManagerDB.getInstance().getConnection();
                PreparedStatement pstmt = con.prepareStatement(Query4);
                pstmt.setString(1, String.valueOf(newQuantity));
                pstmt.setString(2, name);
                pstmt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

