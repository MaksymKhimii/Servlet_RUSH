package controller.command;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

class index implements Command{
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        return "/index.jsp";
    }
}
