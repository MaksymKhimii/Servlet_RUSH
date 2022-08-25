package db;

import db.entity.GoodsArchive;
import db.entity.Receipt;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * RU: слой ДАО для взаимодействия программы с таблицей receipts в базе дынных,
 * которая хранит чеки и информацию о них
 * ENG: DAO layer for program interaction with the receipts table in the melon database,
 * which stores checks and information about them
 */
public class ReceiptsDAO {
    static int Date, Month, Year, Hour, Minute, Second;

    /**
     * RU: проверка, есть ли чек с указаным idreceipt в базе данных
     * ENG: checking if there is a check with the specified idreceipt in the database
     *
     * @param idreceipt receipt id
     * @return status(validation)
     */
    public static boolean validateReceipt(int idreceipt) {
        boolean status = false;
        try {
            String GETUSER = "select * from mydbtest.receipts where idreceipt=?";
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
     * RU: проверка, есть ли продукты с указаным idreceipt в базе данных
     * ENG: checking if there is a products with the specified idreceipt in the database
     *
     * @param idreceipt receipt id
     * @return status(validation)
     */
    public static boolean validateProductsOfReceipt(int idreceipt) {
        boolean status = false;
        try {
            String GETUSER = "select * from mydbtest.goodsarchive where idreceipt=?";
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
     * RU: метод для валидации чека по имени кассира который его создал
     * ENG: method for validating a check by the name of the cashier who created it
     *
     * @param cashier_name cashier's name
     * @return status(validation)
     */
    public static boolean validateReceipt(String cashier_name) {
        boolean status = false;
        try {
            String GETUSER = "SELECT * FROM mydbtest.receipts WHERE cashier_name=?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(GETUSER);
            ps.setString(1, cashier_name);
            try (ResultSet rs = ps.executeQuery()) {
                status = rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * RU: создание нового чека
     * ENG: creating a new check
     *
     * @param cashier_name cashier's name
     */
    public static void addReceipt(String cashier_name) {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String ADD_Product = "INSERT INTO mydbtest.receipts(cashier_name, total_sum, closing_time) VALUES(?, ?, ?);";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(ADD_Product);
            ps.setString(1, cashier_name);
            ps.setString(2, Integer.toString(0));
            ps.setString(3, formatForDateNow.format(date));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * RU: этот метод достает id последнего созданого чека
     * ENG: this method retrieves the id of the last created receipt
     *
     * @return last receipt id
     */
    public static int getLastReceiptId() {
        int idreceipt = 0;
        try {
            String Get_ID = "SELECT idreceipt FROM mydbtest.receipts ORDER BY idreceipt DESC LIMIT 1;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(Get_ID);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                idreceipt = res.getInt("idreceipt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idreceipt;
    }


    /**
     * RU: этот метод достает total_sum чека с указаным idreceipt
     * ENG: this method retrieves the total_sum of the check with the specified idreceipt
     *
     * @param idreceipt receipt id
     * @return total amount per check
     */
    public static double getReceiptSum(int idreceipt) {
        double total_sum = 0;
        try {
            String Get_ID = "SELECT total_sum FROM mydbtest.receipts WHERE idreceipt=?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(Get_ID);
            ps.setString(1, Integer.toString(idreceipt));
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                total_sum = res.getDouble("total_sum");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total_sum;
    }

    /**
     * RU: этот метод прибавляет к total_sum чека с указаным idreceipt
     * ENG: this method adds the check with the specified idreceipt to total_sum
     *
     * @param idreceipt receipt id
     * @param addedSum  the amount to be added to the principal
     */
    public static void addReceiptSum(int idreceipt, double addedSum) {
        double newSum, totalSum = 0;
        try {
            String Query1 = "SELECT total_sum FROM mydbtest.receipts WHERE idreceipt=?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(Query1);
            ps.setString(1, Integer.toString(idreceipt));
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                totalSum = res.getDouble("total_sum");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        newSum = totalSum + addedSum;
        try {
            String Query2 = "UPDATE mydbtest.receipts SET total_sum=? WHERE idreceipt=?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(Query2);
            ps.setString(1, Double.toString(newSum));
            ps.setString(2, Integer.toString(idreceipt));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * RU: этот метод прибавляет к total_sum чека с указаным idreceipt
     * ENG: this method adds the check with the specified idreceipt to total_sum
     *
     * @param idreceipt receipt id
     * @param addedSum  amount to be withdrawn
     */
    public static void minusReceiptSum(int idreceipt, double addedSum) {
        double newSum, totalSum = 0;
        try {
            String Query1 = "SELECT total_sum FROM mydbtest.receipts WHERE idreceipt=?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(Query1);
            ps.setString(1, Integer.toString(idreceipt));
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                totalSum = res.getDouble("total_sum");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        newSum = totalSum - addedSum;
        try {
            String Query2 = "UPDATE mydbtest.receipts SET total_sum=? WHERE idreceipt=?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(Query2);
            ps.setString(1, Double.toString(newSum));
            ps.setString(2, Integer.toString(idreceipt));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * RU: метод закрывает чек, заполняя последнее пустое поле тотальной суммы
     * ENG: the method closes the check by filling in the last empty field of the total amount
     *
     * @param total_sum total sum of receipt
     * @return status of closing
     */
    public static boolean closeReceipt(double total_sum) {

        boolean status = false;
        try {
            String ADD_Product = "INSERT INTO mydbtest.receipts(total_sum) VALUES(?);";
            Connection con = ManagerDB.getInstance().getConnection();

            PreparedStatement ps = con.prepareStatement(ADD_Product);
            ps.setString(1, String.valueOf(total_sum));
            ps.executeUpdate();
            status = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * RU:  метод получает данные всех чеков
     * ENG: the method receives the data of all checks
     *
     * @return ALL Receipts information
     */
    public static ArrayList<Receipt> getAllReceipts() {
        ArrayList<Receipt> receipts = new ArrayList<>() {
        };
        String Query = "SELECT idreceipt, cashier_name, closing_time, total_sum FROM mydbtest.receipts;";
        try {
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(Query);
            ResultSet rs = pstmt.executeQuery();
            boolean found = false;
            while (rs.next()) {
                Receipt AllReceipt = new Receipt();
                AllReceipt.setIdreceipt(rs.getInt("idreceipt"));
                AllReceipt.setCashier_name(rs.getString("cashier_name"));
                AllReceipt.setClosing_time(rs.getString("closing_time"));
                AllReceipt.setTotal_sum(rs.getDouble("total_sum"));
                receipts.add(AllReceipt);
                found = true;
            }
            rs.close();
            pstmt.close();
            if (found) {
                return receipts;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receipts;
    }

    /**
     * RU: метод получает данные одного чека по указаному id
     * ENG: the method receives the data of one check by the specified id
     *
     * @param idreceipt receipt id
     * @return All information about one Receipts
     */
    public static ArrayList<Receipt> getReceiptsByID(int idreceipt) {
        ArrayList<Receipt> receipts = new ArrayList<>() {
        };
        String Query = "SELECT idreceipt, cashier_name, closing_time, total_sum FROM mydbtest.receipts WHERE idreceipt=?;";
        try {
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(Query);
            pstmt.setString(1, Integer.toString(idreceipt));
            ResultSet rs = pstmt.executeQuery();
            boolean found = false;
            while (rs.next()) {
                Receipt AllReceipt = new Receipt();
                AllReceipt.setIdreceipt(rs.getInt("idreceipt"));
                AllReceipt.setCashier_name(rs.getString("cashier_name"));
                AllReceipt.setClosing_time(rs.getString("closing_time"));
                AllReceipt.setTotal_sum(rs.getDouble("total_sum"));
                receipts.add(AllReceipt);
                found = true;
            }
            rs.close();
            pstmt.close();
            if (found) {
                return receipts;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receipts;
    }

    /**
     * RU: метод получает данные о продуктах одного чека, которая хранится в Goodsarchive
     * ENG: the method receives data about the products of one check, which is stored in Goodsarchive
     *
     * @param idreceipt receipt id
     * @return All information about the products of this check
     */
    public static ArrayList<GoodsArchive> getReceiptsProdByID(int idreceipt) {
        ArrayList<GoodsArchive> goods = new ArrayList<>() {
        };
        String Query = "SELECT idreceipt, idproduct, name, quantity, weight, tonnage, price FROM mydbtest.goodsarchive WHERE idreceipt=?;";
        try {
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(Query);
            pstmt.setString(1, Integer.toString(idreceipt));
            ResultSet rs = pstmt.executeQuery();
            boolean found = false;
            while (rs.next()) {
                GoodsArchive AllGoods = new GoodsArchive();
                AllGoods.setIdreceipt(rs.getInt("idreceipt"));
                AllGoods.setIdproduct(rs.getInt("idproduct"));
                AllGoods.setName(rs.getString("name"));
                AllGoods.setQuantity(rs.getInt("quantity"));
                AllGoods.setWeight(rs.getDouble("weight"));
                AllGoods.setTonnage(rs.getBoolean("tonnage"));
                AllGoods.setPrice(rs.getDouble("price"));
                goods.add(AllGoods);
                found = true;
            }
            rs.close();
            pstmt.close();
            if (found) {
                return goods;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    /**
     * RU: метод получает данные одного чека по указаному имени кассира, что создал его
     * ENG: the method receives the data of one check by the specified name of the cashier who created it
     *
     * @param cashier_name casier name
     * @return All information about the products of this check
     */
    public static ArrayList<Receipt> getReceiptsByCashierName(String cashier_name) {
        ArrayList<Receipt> receipts = new ArrayList<>() {
        };
        String Query = "SELECT idreceipt, cashier_name, closing_time, total_sum FROM mydbtest.receipts WHERE cashier_name=?;";
        try {
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(Query);
            pstmt.setString(1, cashier_name);
            ResultSet rs = pstmt.executeQuery();
            boolean found = false;
            while (rs.next()) {
                Receipt AllReceipt = new Receipt();
                AllReceipt.setIdreceipt(rs.getInt("idreceipt"));
                AllReceipt.setCashier_name(rs.getString("cashier_name"));
                AllReceipt.setClosing_time(rs.getString("closing_time"));
                AllReceipt.setTotal_sum(rs.getDouble("total_sum"));
                receipts.add(AllReceipt);
                found = true;
            }
            rs.close();
            pstmt.close();
            if (found) {
                return receipts;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receipts;
    }

    /**
     * RU: метод получает данные одного чека по указанй сумме чека
     * ENG:     the method receives the data of one check by the specified check amount
     *
     * @param total_sum total sum
     * @return All information about the products of this check
     */
    public static ArrayList<Receipt> getReceiptsByTotalSum(double total_sum) {
        ArrayList<Receipt> receipts = new ArrayList<>() {
        };
        String Query = "SELECT idreceipt, cashier_name, total_sum FROM mydbtest.receipts WHERE total_sum=?;";
        try {
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(Query);
            pstmt.setString(1, Double.toString(total_sum));
            ResultSet rs = pstmt.executeQuery();
            boolean found = false;
            while (rs.next()) {
                Receipt AllReceipt = new Receipt();
                AllReceipt.setIdreceipt(rs.getInt("idreceipt"));
                AllReceipt.setCashier_name(rs.getString("cashier_name"));
                AllReceipt.setTotal_sum(rs.getDouble("total_sum"));
                receipts.add(AllReceipt);
                found = true;
            }
            rs.close();
            pstmt.close();
            if (found) {
                return receipts;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receipts;
    }

    /**
     * RU: метод возращает правду если обьект является числом и ложь - если строкой
     * так происходит определение поиска: по id чека или имени кассира
     * ENG: the method returns true if the object is a number and false if it is a string
     * this is how the search is defined: by receipt id или name cashier
     *
     * @param strNum string or number to check
     * @return status
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * RU: удаление чека
     * ENG: deleting a Receipt
     *
     * @param idreceipt receipt id
     * @return deleting status
     */
    public static boolean deleteReceipt(int idreceipt) throws SQLException {
        boolean answer = false;
        try {
            String QUERY1 = "DELETE FROM mydbtest.receipts WHERE idreceipt =?;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps1 = con.prepareStatement(QUERY1);
            ps1.setString(1, String.valueOf(idreceipt));
            ps1.executeUpdate();
            GoodsArchiveDAO.deleteReceiptFromArchive(idreceipt);
            answer = !ReceiptsDAO.validateReceipt(idreceipt) && !GoodsArchiveDAO.validateReceipt(idreceipt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

    /**
     * RU: метод получает данные для отчета
     * ENG: method receives data for the report
     *
     * @return info for report
     */
    public static ArrayList getXSum() throws ParseException, SQLException {
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");
        double XSum = 0;
        int receipts = 0;
        int lastIdReceipt = 0;
        ArrayList X = new ArrayList();
        String Query1 = "SELECT idreceipt, closing_time, total_sum FROM mydbtest.receipts;";
        Connection con = ManagerDB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(Query1);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int idreceipt = rs.getInt("idreceipt");
            Date baseDate0 = rs.getDate("closing_time");
            double total_sum = rs.getDouble("total_sum");
            String basedate = formattedDate.format(baseDate0);
            Date datenow0 = new Date();
            String datenow = formattedDate.format(datenow0);
            if (basedate.equals(datenow)) {
                receipts++;
                XSum = XSum + total_sum;
                lastIdReceipt = idreceipt;
            }
        }
        X.add(receipts);
        X.add(lastIdReceipt);
        X.add(XSum);
        return X;
    }

    /**
     * RU: метод получает текущую дату
     * ENG: method gets current date
     *
     * @return ArrayList Date
     */
    public static ArrayList GetCurrentDate() {
        ArrayList date = new ArrayList();
        Calendar calendar = new GregorianCalendar();
        Date Datenow = calendar.getTime();
        date.add(0, Year = Datenow.getYear() + 1900);
        date.add(1, Month = Datenow.getMonth());
        date.add(2, Date = Datenow.getDate());
        date.add(3, Hour = Datenow.getHours());
        date.add(4, Minute = Datenow.getMinutes());
        date.add(5, Second = Datenow.getSeconds());
        return date;
    }
}