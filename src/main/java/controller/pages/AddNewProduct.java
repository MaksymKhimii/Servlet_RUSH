package controller.pages;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


public class AddNewProduct implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        return "/WEB-INF/admin-basic/add_new_product.jsp";
    }

    @Override
    public String toString() {
        return "add_new_product";
    }
}
