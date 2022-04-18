package controller.pages;

import controller.command.Command;
import db.ReceiptsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

                  ReceiptsDAO.GetCurrentDate().get(0)+"-"+
                  ReceiptsDAO.GetCurrentDate().get(1)+"-"+
                  ReceiptsDAO.GetCurrentDate().get(2)+" "+
                  ReceiptsDAO.GetCurrentDate().get(3)+":"+
                  ReceiptsDAO.GetCurrentDate().get(4)+":"+
                  ReceiptsDAO.GetCurrentDate().get(5);

            ArrayList result = ReceiptsDAO.getXSum();
            int countOfReceipts = (int) result.get(0); // колличество чеков в отчете
            int lastIdReceipt = (int) result.get(1);
            double XSum = (double) result.get(2);

           request.setAttribute("currentDate", Current_Date);
            request.setAttribute("countOfReceipts", countOfReceipts);
            request.setAttribute("lastIdReceipt" ,lastIdReceipt);
            request.setAttribute("XSum", XSum);
        }catch (ParseException e){
            e.printStackTrace();
        }
        answer="/WEB-INF/st_cashier-basic/Xreport.jsp";
        return answer;
    }
}
