package controller.pages;

import controller.command.Command;
import db.ReceiptsDAO;
import db.ReportDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/** RU: обработка создания отчета
 * ENG: report generation processing
 */
public class report implements Command {
    private static final Logger log = Logger.getLogger(report.class.getName());
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        try {
            //получаем текущую дату
            SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");
            Date datenow0 = new Date();
            String Current_Date= formattedDate.format(datenow0);

            if(ReportDAO.AllowToReport()){
                try {
                ArrayList result = ReceiptsDAO.getXSum();
                int countOfReceipts = (int) result.get(0);
                int lastIdReceipt = (int) result.get(1);
                double XSum = (double) result.get(2);

                ReportDAO.addReport(countOfReceipts, lastIdReceipt, XSum);
                log.info("The new report has been created");
                request.setAttribute("currentDate", Current_Date);
                request.setAttribute("countOfReceipts", countOfReceipts);
                request.setAttribute("lastIdReceipt" ,lastIdReceipt);
                request.setAttribute("XSum", XSum);

                    answer="/WEB-INF/st_cashier-basic/report.jsp";
            }catch (ParseException e){
                e.printStackTrace();
            }

            } else{
                log.error("The new report hasn't been created(most likely, on this day the report was already created)");
                answer="/WEB-INF/st_cashier-basic/report_error.jsp";
            }

    } catch (ParseException e){
        e.printStackTrace();
        }
        return answer;
    }
}
