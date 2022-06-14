package db;

import db.entity.Report;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/** RU: слой ДАО для взаимодействия программы с таблицей report в базе дынных,
 *      которая хранит отчеты и информацию о них
 * ENG: DAO layer for program interaction with the report table in the melon database,
 *      which stores reports and information about them
 */
public class ReportDAO {

    /** RU: метод для проверки прошли ли сутки с предыдущего запроса отчета
     * ENG: method for checking if a day has passed since the previous report request
     * @return status
     */
    public static boolean AllowToReport() throws SQLException, ParseException {
        boolean status=false;
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");
        Date datenow0 = new Date();
        String datenow= formattedDate.format(datenow0);
        String Query="Select time FROM mydbtest.report ORDER BY idreport DESC LIMIT 1;";
        Connection con = ManagerDB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(Query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            java.util.Date lastdate0=rs.getDate("Time");
            String basedate =formattedDate.format(lastdate0);
            java.util.Date dnow= formattedDate.parse(datenow);
            System.out.println("dnow: "+dnow);
            java.util.Date dbase=formattedDate.parse(basedate);
            System.out.println("dbase: "+dbase);
            if(dbase.compareTo(dnow)<0){
                status = true;
            } else if(dbase.compareTo(dnow)==0){
                status=false;
            }
        }
        return status;
    }


    /** RU: проверка на налачие хотя бы одного нулевого отчета в базе данных
     * ENG: check for at least one null report in databases
     * @return status
     */
    public static boolean checkReport() {
        boolean answer = false;
        try {
            String QUERY = "SELECT * FROM mydbtest.report;";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(QUERY);
            try (ResultSet rs = ps.executeQuery()) {
                answer = rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }


    /** RU: сохранение нового отчета
     * ENG: saving a new report
     * @param QuantityOfReceipts number of checks created in the last day
     * @param LastReceiptId last receipt id
     * @param TotalSum total sum for all these checks
     */
    public static void addReport(int QuantityOfReceipts, int LastReceiptId, double TotalSum) {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datenow0 = new Date();
        String Time= formatForDateNow.format(datenow0);
        try {
            String ADD_Product = "INSERT INTO mydbtest.report (quantityOfReceipts, lastReceiptId, time , totalSum) VALUES(?, ?, ?, ?);";
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(ADD_Product);
            ps.setString(1, String.valueOf(QuantityOfReceipts));
            ps.setString(2, String.valueOf(LastReceiptId));
            ps.setString(3, Time);
            ps.setString(4, String.valueOf(TotalSum));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** RU: метод получает данные всех суточных отчетов
     * ENG: method gets data from all daily reports
     * @return report
     */
    public static ArrayList<Report> getAllReports(){
        ArrayList<Report> reports= new ArrayList<>(){};
        String Query = "SELECT idreport, quantityOfReceipts, lastReceiptId, time, totalSum FROM mydbtest.report;";
        try {
            Connection con = ManagerDB.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(Query);
            ResultSet rs=pstmt.executeQuery();
            boolean found= false;
            while (rs.next()){
                Report AllReports = new Report();
                AllReports.setIdreport(rs.getInt("idreport"));
                AllReports.setQuantityOfReceipts(rs.getInt("quantityOfReceipts"));
                AllReports.setLastReceiptId(rs.getInt("lastReceiptId"));
                AllReports.setTime(rs.getString("time"));
                AllReports.setTotalSum(rs.getDouble("totalSum"));
                reports.add(AllReports);
                found= true;
            }
            rs.close(); pstmt.close();
            if(found){return reports;}
            else {return null;}
        } catch (SQLException e) {
            e.printStackTrace();}
        return reports;
    }
}
