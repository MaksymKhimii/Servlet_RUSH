package controller.pages;



import controller.command.Command;
import db.ReceiptsDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: главная страница старшего кассира
 * ENG: elder cashier home page
 */
public class st_cashier implements Command {
    private static final Logger log = Logger.getLogger(st_cashier.class.getName());
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        request.setAttribute("receipts", ReceiptsDAO.getAllReceipts());
        log.debug("Elder Cashier is working...");
        return "/WEB-INF/st_cashier-basic/st_cashier.jsp";
    }

    @Override
    public String toString() {
        return "st_cashier";
    }
}
