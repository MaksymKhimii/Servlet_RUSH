package controller.pages;

import controller.command.Command;
import db.ProductsDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * RU: главная страница кассира
 * ENG: cashier home page
 */
public class Cashier implements Command {
    private static final Logger log = Logger.getLogger(Cashier.class.getName());

    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        log.debug("Cashier is working...");
        request.setAttribute("products", ProductsDAO.getAllProducts());

        return "/WEB-INF/user-basic/cashier.jsp";
    }

    @Override
    public String toString() {
        return "cashier";
    }
}
