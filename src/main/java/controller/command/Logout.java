package controller.command;

import db.enums.UserRole;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/** RU: обработка выхода пользователя и завершение текущего сеанса
 * ENG: processing user logout and end current session
 */
public class Logout implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        CommandUtility.setUserRole(request, UserRole.unknown, "Guest");
        return "/index.jsp";
    }
}