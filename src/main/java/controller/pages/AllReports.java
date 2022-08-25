package controller.pages;

import controller.command.Command;
import db.ReportDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * RU: обработка получения и отображения всех отчетов, сохраненных в бд
 * ENG: processing the receipt and display of all reports stored in the database
 */
public class AllReports implements Command {
    private static final Logger log = Logger.getLogger(AllReports.class.getName());

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer;
        if (ReportDAO.checkReport()) {
            log.debug("All reports has been got");
            request.setAttribute("reports", ReportDAO.getAllReports());
            answer = "/WEB-INF/st_cashier-basic/AllReports.jsp";
        } else {
            log.error("Reports hasn't been got");
            answer = "/WEB-INF/st_cashier-basic/receiptSearchError.jsp";
        }
        return answer;
    }

    @Override
    public String toString() {
        return "AllReports";
    }
}
