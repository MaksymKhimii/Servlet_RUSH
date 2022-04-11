package controller.pages;

import controller.command.Command;
import db.ReceipsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Xreport implements Command {
    Date myDay;
    int Date;
    int Month;
    int Year;
 //   Calendar calendar = new GregorianCalendar();
    SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        //TODO  сделать обработку и отражение отчета
        try {
            //получаем текущую дату
          String Current_Date=

                  ReceipsDAO.GetCurrentDate().get(0)+"-"+
                  ReceipsDAO.GetCurrentDate().get(1)+"-"+
                  ReceipsDAO.GetCurrentDate().get(2)+" "+
                  ReceipsDAO.GetCurrentDate().get(3)+":"+
                  ReceipsDAO.GetCurrentDate().get(4)+":"+
                  ReceipsDAO.GetCurrentDate().get(5);

            ArrayList result = ReceipsDAO.getXSum();
            int countOfReceipts = (int) result.get(0); // колличество чеков в отчете
            int lastIdReceipt = (int) result.get(1);
            double XSum = (double) result.get(2);

           request.setAttribute("currentDate", Current_Date);
            request.setAttribute("countOfReceipts", countOfReceipts);
            request.setAttribute("lastIdReceipt" ,lastIdReceipt);
            request.setAttribute("XSum", XSum);
          //  request.setAttribute("currentDate", currentDate);

            System.out.println("Method: "+result);
            System.out.println("countOfReceipts: "+countOfReceipts);
            System.out.println("lastIdReceipt: "+lastIdReceipt);
            System.out.println("XSum: "+XSum);
        }catch (ParseException e){
            e.printStackTrace();
        }
        answer="/WEB-INF/st_cashier-basic/Xreport.jsp";
        return answer;
    }
}
