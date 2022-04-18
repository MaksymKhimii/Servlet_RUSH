package controller.pages;

import controller.command.Command;
import db.ProductsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: главная страница кассира
 * ENG: cashier home page
 */
public class cashier implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {

        request.setAttribute("products", ProductsDAO.getAllProducts());
        return "/WEB-INF/user-basic/cashier.jsp";
    }
}
