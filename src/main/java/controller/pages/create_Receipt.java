package controller.pages;

import controller.command.Command;
import db.ProductsDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class create_Receipt implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        request.setAttribute("products", ProductsDao.getAllProducts());
        return "/WEB-INF/user-basic/createReceipt.jsp";
    }
}
