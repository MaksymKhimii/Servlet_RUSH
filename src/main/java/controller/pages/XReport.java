package controller.pages;

import controller.command.Command;
import db.ReceiptsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/** RU: обработка создания Х-отчета
 * ENG: X-report generation processing
 */
public class XReport implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer;
        try {
            //get current date
          String Current_Date=

                  ReceiptsDAO.GetCurrentDate().get(0)+"-"+
                  ReceiptsDAO.GetCurrentDate().get(1)+"-"+
                  ReceiptsDAO.GetCurrentDate().get(2)+" "+
                  ReceiptsDAO.GetCurrentDate().get(3)+":"+
                  ReceiptsDAO.GetCurrentDate().get(4)+":"+
                  ReceiptsDAO.GetCurrentDate().get(5);

            ArrayList result = ReceiptsDAO.getXSum();
            int countOfReceipts = (int) result.get(0);
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

    @Override
    public String toString() {
        return "Xreport";
    }
}
