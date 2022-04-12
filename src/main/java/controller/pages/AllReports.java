package controller.pages;

import controller.command.Command;
import db.ReportDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AllReports implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer=null;

        if(ReportDAO.checkReport()) {
            request.setAttribute("REPORTS", ReportDAO.getAllReports());
            answer="/WEB-INF/st_cashier-basic/AllReports.jsp";
        } else {
            answer="/WEB-INF/st_cashier-basic/receiptSearchError.jsp";
        }
        return answer;
    }
}
