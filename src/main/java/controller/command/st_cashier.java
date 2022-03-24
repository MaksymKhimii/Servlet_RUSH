package controller.command;



import db.UserDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


public class st_cashier implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        request.setAttribute("users", UserDao.getAllUsers());
        return "/WEB-INF/st_cashier-basic/st_cashier.jsp";
    }
}
