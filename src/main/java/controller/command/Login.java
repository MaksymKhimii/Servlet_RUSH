package controller.command;

import db.UserDAO;
import db.enums.UserRole;
import org.apache.log4j.Logger;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * RU: обработка входа юзера
 * ENG: user login processing
 */
public class Login implements Command {
    private static final Logger log = Logger.getLogger(Login.class.getName());

    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        String errorMessage;
        HttpSession session = request.getSession();
        String name = request.getParameter("username");
        session.setAttribute("username", name);
        String pass = request.getParameter("password");
        String answer = null;

        if (UserDAO.validate(name, pass)) {
            log.info("User was found");
            if (UserDAO.getRole(name, pass).equals(UserRole.merchandiser.toString())) {
                CommandUtility.setUserRole(request, UserRole.merchandiser, name);
                answer = "redirect:/merchandiser";
            } else if (UserDAO.getRole(name, pass).equals(UserRole.cashier.toString())) {
                CommandUtility.setUserRole(request, UserRole.cashier, name);
                answer = "redirect:/cashier";
            } else if (UserDAO.getRole(name, pass).equals(UserRole.st_cashier.toString())) {
                CommandUtility.setUserRole(request, UserRole.st_cashier, name);
                answer = "redirect:/st_cashier";
            }
        } else {
            errorMessage = "Login/password cannot be empty";
            log.error("errorMessage --> " + errorMessage);
            answer = "/index.jsp";
        }
        return answer;
    }


    @Override
    public String toString() {
        return "Login";
    }
}

