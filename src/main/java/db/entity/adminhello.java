package db.entity;

import controller.command.Command;
import db.ProductsDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class adminhello implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        request.setAttribute("products", ProductsDao.getAllProducts());
        return "/WEB-INF/admin-basic/adminhello.jsp";
    }

}
