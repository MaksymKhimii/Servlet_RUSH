package controller.command;

import db.UserDAO;
import db.enums.UserRole;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class Login implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {

        HttpSession session = request.getSession();
        String name = request.getParameter("username");
        session.setAttribute("username", name);
        String username = (String) session.getAttribute("username");
        String pass = request.getParameter("password");
        String answer = null;

        if (UserDAO.validate(name, pass)) {
            if (UserDAO.getRole(name, pass).equals(UserRole.merchandiser.toString())) {
                CommandUtility.setUserRole(request, UserRole.merchandiser, name);
                answer = "redirect:/merchandiser";
            } else if (UserDAO.getRole(name, pass).equals(UserRole.cashier.toString())) {
               CommandUtility.setUserRole(request, UserRole.cashier, name);
                answer = "redirect:/cashier";
            } else if(UserDAO.getRole(name, pass).equals(UserRole.st_cashier.toString())){
                CommandUtility.setUserRole(request, UserRole.st_cashier, name);
                answer = "redirect:/st_cashier";
            }
        } else {
            answer = "/index.jsp";
        }
        return answer;
    }
}

