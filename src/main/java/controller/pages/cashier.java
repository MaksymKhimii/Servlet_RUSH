package controller.pages;

import controller.command.Command;
import db.ProductsDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class cashier implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {

        request.setAttribute("products", ProductsDao.getAllProducts());
        return "/WEB-INF/user-basic/cashier.jsp";
    }
}
