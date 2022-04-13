package controller.pages;

import controller.command.Command;
import db.ProductsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class merchandiser implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        request.setAttribute("products", ProductsDAO.getAllProducts());
        return "/WEB-INF/admin-basic/merchandiser.jsp";
    }

}
