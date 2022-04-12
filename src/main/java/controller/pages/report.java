package controller.pages;

import controller.command.Command;
import db.ReceipsDAO;
import db.ReportDAO;
import db.entity.Report;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class report implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        try {
            //получаем текущую дату
            SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");
            Date datenow0 = new Date();
            String Current_Date= formattedDate.format(datenow0);

            System.out.println("разрешение: "+ReportDAO.AllowToReport());
            if(ReportDAO.AllowToReport()){
                try {
                ArrayList result = ReceipsDAO.getXSum();
                int countOfReceipts = (int) result.get(0); // колличество чеков в отчете
                int lastIdReceipt = (int) result.get(1);
                double XSum = (double) result.get(2);

                ReportDAO.addReport(countOfReceipts, lastIdReceipt, XSum);
                request.setAttribute("currentDate", Current_Date);
                request.setAttribute("countOfReceipts", countOfReceipts);
                request.setAttribute("lastIdReceipt" ,lastIdReceipt);
                request.setAttribute("XSum", XSum);

                    answer="/WEB-INF/st_cashier-basic/report.jsp";
            }catch (ParseException e){
                e.printStackTrace();
            }

            } else{
                answer="/WEB-INF/st_cashier-basic/report_error.jsp";
            }

    } catch (ParseException e){
        e.printStackTrace();
        }
        return answer;
    }
}
