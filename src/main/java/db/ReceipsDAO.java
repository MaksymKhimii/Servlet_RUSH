package db;

import db.entity.Goodsarchive;
import db.entity.Receipt;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ReceipsDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "Maks_Khimii";
    private static final String PASSWORD = "makskhimiy24112003";
    static int Date, DateNow, Month, MonthNow, Year, YearNow, Hour, HourNow, Minute, MinuteNow, Second, SecondNow;

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

    /**метод для валидации чека по имени кассира который его создал*/
    public static boolean validateReceipt(String cashier_name){
        boolean status=false;
        try{
            String GETUSER="SELECT * FROM mydbtest.receipts WHERE cashier_name=?;";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps=con.prepareStatement(GETUSER);
            ps.setString(1,cashier_name);
            try (ResultSet rs=ps.executeQuery()){
                status=rs.next();
            }
        }catch(SQLException | ClassNotFoundException e){e.printStackTrace();}
        return status;
    }

    //TODO добавить информацию о поле времени создания чека
    /** создание нового чека*/
    public static void addReceipt(String cashier_name) throws SQLException, ClassNotFoundException {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(formatForDateNow.format(date));

        boolean status=false;
        try {
            String ADD_Product = "INSERT INTO mydbtest.receipts(cashier_name, total_sum, closing_time) VALUES(?, ?, ?);";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement ps = con.prepareStatement(ADD_Product);
            ps.setString(1, cashier_name);
            ps.setString(2, Integer.toString(0));
            ps.setString(3, formatForDateNow.format(date));
            ps.executeUpdate();
            status=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    /**этот метод достает total_sum чека с указаным idreceipt*/
    public static double getReceiptSum(int idreceipt) throws ClassNotFoundException, SQLException {
        double total_sum=0;
        try {
            String Get_ID = "SELECT total_sum FROM mydbtest.receipts WHERE idreceipt=?;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps= con.prepareStatement(Get_ID);
            ps.setString(1, Integer.toString(idreceipt));
            ResultSet res=ps.executeQuery();
            while (res.next()){
                total_sum=res.getDouble("total_sum");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total_sum;
    }

    /**этот метод прибавляет к total_sum чека с указаным idreceipt*/
    public static void addReceiptSum(int idreceipt, double addedSum) throws ClassNotFoundException, SQLException {
        double newSum, totalSum=0;
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
        }catch (SQLException e){
            e.printStackTrace();
        }
        newSum=totalSum+addedSum;
        try {
            String Query2 = "UPDATE mydbtest.receipts SET total_sum=? WHERE idreceipt=?;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps= con.prepareStatement(Query2);
            ps.setString(1, Double.toString(newSum));
            ps.setString(2,Integer.toString(idreceipt) );
            int res=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**этот метод прибавляет к total_sum чека с указаным idreceipt*/
    public static void minusReceiptSum(int idreceipt, double addedSum) throws ClassNotFoundException, SQLException {
        double newSum, totalSum=0;
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
        }catch (SQLException e){
            e.printStackTrace();
        }
        newSum=totalSum-addedSum;
        try {
            String Query2 = "UPDATE mydbtest.receipts SET total_sum=? WHERE idreceipt=?;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps= con.prepareStatement(Query2);
            ps.setString(1, Double.toString(newSum));
            ps.setString(2,Integer.toString(idreceipt) );
            int res=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    /**тут мы закрываем чек, но нужно будет сделать очистку таблицы basket*/
    public static boolean closeReceipt(double total_sum) throws SQLException, ClassNotFoundException {

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


    /** метод получает данные всех чеков */
    public static ArrayList<Receipt> getAllReceipts(){
        ArrayList<Receipt> receipts= new ArrayList<Receipt>(){};
        String Query = "SELECT idreceipt, cashier_name, closing_time, total_sum FROM mydbtest.receipts;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(Query);
            ResultSet rs=pstmt.executeQuery();
            boolean found= false;
            while (rs.next()){
                Receipt AllReceipt = new Receipt();
                AllReceipt.setIdreceipt(rs.getInt("idreceipt"));
                AllReceipt.setCashier_name(rs.getString("cashier_name"));
                AllReceipt.setClosing_time(rs.getString("closing_time"));
                AllReceipt.setTotal_sum(rs.getDouble("total_sum"));
                receipts.add(AllReceipt);
                found= true;
            }
            rs.close(); pstmt.close();
            if(found){return receipts;}
            else {return null;}
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();}
        return receipts;
    }

    /** метод получает данные одного чека по указаному id */
    public static ArrayList<Receipt> getReceiptsByID(int idreceipt){
        ArrayList<Receipt> receipts= new ArrayList<Receipt>(){};
        String Query = "SELECT idreceipt, cashier_name, closing_time, total_sum FROM mydbtest.receipts WHERE idreceipt=?;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(Query);
            pstmt.setString(1, Integer.toString(idreceipt));
            ResultSet rs=pstmt.executeQuery();
            boolean found= false;
            while (rs.next()){
                Receipt AllReceipt = new Receipt();
                AllReceipt.setIdreceipt(rs.getInt("idreceipt"));
                AllReceipt.setCashier_name(rs.getString("cashier_name"));
                AllReceipt.setClosing_time(rs.getString("closing_time"));
                AllReceipt.setTotal_sum(rs.getDouble("total_sum"));
                receipts.add(AllReceipt);
                found= true;
            }
            rs.close(); pstmt.close();
            if(found){return receipts;}
            else {return null;}
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();}
        return receipts;
    }

    /**метод получает данные о продуктах одного чека*/
    public static ArrayList<Goodsarchive> getReceiptsProdByID(int idreceipt){
        ArrayList<Goodsarchive> goods= new ArrayList<Goodsarchive>(){};
        String Query = "SELECT idreceipt, idproduct, name, quantity, weight, tonnage, price FROM mydbtest.goodsarchive WHERE idreceipt=?;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(Query);
            pstmt.setString(1, Integer.toString(idreceipt));
            ResultSet rs=pstmt.executeQuery();
            boolean found= false;
            while (rs.next()){
               Goodsarchive AllGoods= new Goodsarchive();
                AllGoods.setIdreceipt(rs.getInt("idreceipt"));
                AllGoods.setIdproduct(rs.getInt("idproduct"));
                AllGoods.setName(rs.getString("name"));
                AllGoods.setQuantity(rs.getInt("quantity"));
                AllGoods.setWeight(rs.getDouble("weight"));
                AllGoods.setTonnage(rs.getBoolean("tonnage"));
                AllGoods.setPrice(rs.getDouble("price"));
                goods.add(AllGoods);
                found= true;
            }
            rs.close(); pstmt.close();
            if(found){return goods;}
            else {return null;}
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();}
        return goods;
    }

    /** метод получает данные одного чека по указаному имени кассира, что создал его */
    public static ArrayList<Receipt> getReceiptsByCashierName(String cashier_name){
        ArrayList<Receipt> receipts= new ArrayList<Receipt>(){};
        String Query = "SELECT idreceipt, cashier_name, closing_time, total_sum FROM mydbtest.receipts WHERE cashier_name=?;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(Query);
            pstmt.setString(1, cashier_name);
            ResultSet rs=pstmt.executeQuery();
            boolean found= false;
            while (rs.next()){
                Receipt AllReceipt = new Receipt();
                AllReceipt.setIdreceipt(rs.getInt("idreceipt"));
                AllReceipt.setCashier_name(rs.getString("cashier_name"));
                AllReceipt.setClosing_time(rs.getString("closing_time"));
                AllReceipt.setTotal_sum(rs.getDouble("total_sum"));
                receipts.add(AllReceipt);
                found= true;
            }
            rs.close(); pstmt.close();
            if(found){return receipts;}
            else {return null;}
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();}
        return receipts;
    }

    /** метод получает данные одного чека по указанй сумме чека */
    public static ArrayList<Receipt> getReceiptsByTotalSum(double total_sum){
        ArrayList<Receipt> receipts= new ArrayList<Receipt>(){};
        String Query = "SELECT idreceipt, cashier_name, total_sum FROM mydbtest.receipts WHERE total_sum=?;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(Query);
            pstmt.setString(1, Double.toString(total_sum));
            ResultSet rs=pstmt.executeQuery();
            boolean found= false;
            while (rs.next()){
                Receipt AllReceipt = new Receipt();
                AllReceipt.setIdreceipt(rs.getInt("idreceipt"));
                AllReceipt.setCashier_name(rs.getString("cashier_name"));
                AllReceipt.setTotal_sum(rs.getDouble("total_sum"));
                receipts.add(AllReceipt);
                found= true;
            }
            rs.close(); pstmt.close();
            if(found){return receipts;}
            else {return null;}
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();}
        return receipts;
    }

    /**метод возращает правду если обьект является числом и ложь - если строкой
     * так происходит определение поиска: по idreceipt или name_cashier */
    public static boolean isNumeric(String strNum){
            if (strNum == null) {
                return false;
            }
            try {
                double d = Double.parseDouble(strNum);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
    }

    /**удаление чека*/
    public static boolean deleteReceipt(int idreceipt) throws SQLException, ClassNotFoundException {
        boolean answer = false;
        try {
            //  удаляем из таблицы чеков
            String QUERY1="DELETE FROM mydbtest.receipts WHERE idreceipt =?;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement ps1=con.prepareStatement(QUERY1);
            ps1.setString(1, String.valueOf(idreceipt));
            ps1.executeUpdate();
            //удаляем товар из таблицы goodsarchive
            GoodsArchiveDAO.deleteReceiptFromArchive(idreceipt);

            if(ReceipsDAO.validateReceipt(idreceipt) || GoodsArchiveDAO.validateReceipt(idreceipt)){
                answer=false;
            } else {
                answer=true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return answer;
    }

    public static ArrayList getXSum() throws ParseException, ClassNotFoundException, SQLException {
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");
        double XSum = 0;
        int receipts = 0; //колличество чеков в отчете
        int lastIdReceipt = 0;// id последнего чека
        ArrayList X = new ArrayList();
        String Query1 = "SELECT idreceipt, closing_time, total_sum FROM mydbtest.receipts;";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        PreparedStatement ps = con.prepareStatement(Query1);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int idreceipt = rs.getInt("idreceipt");
            Date baseDate0 = rs.getDate("closing_time");
            double total_sum = rs.getDouble("total_sum");
            String basedate =formattedDate.format(baseDate0);
            //текущая дата
            Date datenow0 = new Date();
            String datenow= formattedDate.format(datenow0);

            if(basedate.equals(datenow)){
                receipts++;
                XSum=XSum+total_sum;
                lastIdReceipt=idreceipt;
            }

        }
        X.add(receipts);
        X.add(lastIdReceipt);
        X.add(XSum);
        return X;
    }

    public static ArrayList GetCurrentDate(){
        ArrayList date = new ArrayList();
        //получаем текущую дату

        Calendar calendar = new GregorianCalendar();
        Date Datenow= calendar.getTime();
        date.add(0,Year = Datenow.getYear()+1900);
        date.add(1, Month = Datenow.getMonth());
        date.add(2, Date = Datenow.getDate());
        date.add(3, Hour = Datenow.getHours());
        date.add(4, Minute = Datenow.getMinutes());
        date.add(5, Second=Datenow.getSeconds());
      //  date=Year+"-"+Month+"-"+Date+" "+Hour+":"+Minute+":"+Second;
        return date;
    }



}
