package controller.pages;

import controller.command.Command;
import db.ReportDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: обработка получения и отображения всех отчетов, сохраненных в бд
 * ENG: processing the receipt and display of all reports stored in the database
 */
public class AllReports implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer=null;
        if(ReportDAO.checkReport()) {
            request.setAttribute("reports", ReportDAO.getAllReports());
            answer="/WEB-INF/st_cashier-basic/AllReports.jsp";
        } else {
            answer="/WEB-INF/st_cashier-basic/receiptSearchError.jsp";
        }
        return answer;
    }
}
