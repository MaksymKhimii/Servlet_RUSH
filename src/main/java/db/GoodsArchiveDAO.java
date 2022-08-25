package db;

import java.sql.*;

/**
 * RU: слой ДАО для взаимодействия программы с таблицей GoodsArchive в базе дынных,
 * которая хранит данные продуктов со всех чеков
 * ENG: a DAO layer for the interaction of the program with the GoodsArchive table in the melon database,
 * * which stores product data from all checks
 */
public class GoodsArchiveDAO {
    /**
     * RU: переносит данные чека из basket в goodsarchive для сохранения
     * ENG: transfers receipt data from basket to goodsarchive for saving
     *
     * @param idreceipt receipt id
     */
    public static void saveBacket(int idreceipt) {
        double total_sum = ReceiptsDAO.getReceiptSum(idreceipt);
        try {
            Connection con = ManagerDB.getInstance().getConnection();
            String QUERY1 = "INSERT INTO mydbtest.goodsarchive(idreceipt, idproduct, name, quantity, weight, tonnage, price)" +
                    " SELECT idreceipt, idproduct, name, quantity, weight, tonnage, price FROM mydbtest.basket WHERE idreceipt=?;";
            PreparedStatement ps1 = con.prepareStatement(QUERY1);
            ps1.setString(1, String.valueOf(idreceipt));
            ps1.executeUpdate();
            String QUERY2 = "UPDATE  mydbtest.goodsarchive SET total_sum=? WHERE idreceipt=?;";
            PreparedStatement ps2 = con.prepareStatement(QUERY2);
            ps2.setString(1, String.valueOf(total_sum));
            ps2.setString(2, String.valueOf(idreceipt));
            ps2.executeUpdate();
            BasketDAO.deleteBasket(idreceipt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * RU: удаляем продукты чека по его idreceipt
     * ENG: delete check products by its idreceipt
     *
     * @param idreceipt receipt id
     */
    public static void deleteReceiptFromArchive(int idreceipt) throws SQLException {
        try {
            String QUERY = "DELETE FROM mydbtest.goodsarchive WHERE idreceipt =?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(QUERY);
            ps.setString(1, String.valueOf(idreceipt));
            ps.executeUpdate();
            GoodsArchiveDAO.validateReceipt(idreceipt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * RU: удаляем продукты чека по его idreceipt
     * ENG: delete check products by its idreceipt
     *
     * @param idproduct product id
     * @param idreceipt receipt id
     */
    public static void deleteProdFromReceipt(int idproduct, int idreceipt) {
        try {
            String QUERY = "DELETE FROM mydbtest.goodsarchive WHERE idproduct=? AND idreceipt=?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(QUERY);
            ps.setString(1, String.valueOf(idproduct));
            ps.setString(2, String.valueOf(idreceipt));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * RU: проверка, есть ли чек с указаным idreceipt в архиве
     * ENG: checking if there is a receipt with the specified receipt id in the archive
     *
     * @param idreceipt receipt id
     * @return boolean
     */
    public static boolean validateReceipt(int idreceipt) {
        boolean status = false;
        try {
            String GETUSER = "SELECT * FROM mydbtest.goodsarchive WHERE idreceipt=?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(GETUSER);
            ps.setString(1, String.valueOf(idreceipt));
            try (ResultSet rs = ps.executeQuery()) {
                status = rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * RU: проверка, есть ли продукт с указаным idproduct в чеке idreceipt
     * ENG: checking if there is a product with the specified idproduct in the idreceipt
     *
     * @param idproduct product id
     * @param idreceipt receipt id
     * @return boolean
     */
    public static boolean validateProdInReceipt(int idproduct, int idreceipt) {
        boolean status = false;
        try {
            String GETUSER = "SELECT * FROM mydbtest.goodsarchive WHERE idproduct=? AND idreceipt=?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(GETUSER);
            ps.setString(1, String.valueOf(idproduct));
            ps.setString(2, String.valueOf(idreceipt));
            try (ResultSet rs = ps.executeQuery()) {
                status = rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * RU: считает сумму продукта который будет удален из чека
     * ENG: calculates the amount of the product that will be removed from the receipt
     *
     * @param idreceipt receipt id
     * @param idproduct product id
     * @return double
     */
    public static double getCostOneProduct(int idreceipt, int idproduct) {
        int quantity = 0;
        boolean tonnage = false;
        double weight = 0;
        double price = 0;
        try {
            String GETUSER = "SELECT quantity, weight, tonnage, price FROM mydbtest.goodsarchive WHERE idreceipt=? AND idproduct=?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(GETUSER);
            ps.setString(1, String.valueOf(idreceipt));
            ps.setString(2, String.valueOf(idproduct));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    quantity = rs.getInt("quantity");
                    weight = rs.getDouble("weight");
                    tonnage = rs.getBoolean("tonnage");
                    price = rs.getDouble("price");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        double cost;
        if (tonnage) {
            cost = weight * price;
        } else {
            cost = quantity * price;
        }
        return cost;
    }

    /**
     * RU: обновляет сумму в таблице goodsarchive
     * ENG: updates the total_sum in the goodsarchive table
     *
     * @param idreceipt receipt id
     */
    public static void updateSum(int idreceipt) {
        double totalSum = 0;
        try {
            String Query1 = "SELECT total_sum FROM mydbtest.receipts WHERE idreceipt=?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(Query1);
            ps.setString(1, Integer.toString(idreceipt));
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                totalSum = res.getDouble("total_sum");
            }
            String Query2 = "UPDATE mydbtest.goodsarchive SET total_sum=? WHERE idreceipt=?;";
            Connection con2 = ManagerDB.getInstance().getConnection();
            PreparedStatement ps2 = con2.prepareStatement(Query2);
            ps2.setString(1, Double.toString(totalSum));
            ps2.setString(2, Integer.toString(idreceipt));
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}