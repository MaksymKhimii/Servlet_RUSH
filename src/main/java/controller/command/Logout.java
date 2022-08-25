package controller.command;

import db.enums.UserRole;

import javax.servlet.http.*;


/**
 * RU: обработка выхода пользователя и завершение текущего сеанса
 * ENG: processing user logout and end current session
 */
public class Logout implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        CommandUtility.setUserRole(request, UserRole.unknown, "Guest");
        return "/index.jsp";
    }

    @Override
    public String toString() {
        return "Logout";
    }
}