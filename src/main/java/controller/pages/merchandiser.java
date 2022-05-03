package controller.pages;

import controller.command.Command;
import db.ProductsDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: главная страница товароведа
 * ENG: merchandiser home page
 */
public class merchandiser implements Command {
    private static final Logger log = Logger.getLogger(merchandiser.class.getName());
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        request.setAttribute("products", ProductsDAO.getAllProducts());
        log.debug("Merchandiser is working...");
        return "/WEB-INF/admin-basic/merchandiser.jsp";
    }

    @Override
    public String toString() {
        return "merchandiser";
    }
}
