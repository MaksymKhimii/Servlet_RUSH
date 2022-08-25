package controller.command;

import db.enums.UserRole;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * RU: класс для определения атрибутов пользователя
 * ENG: class to define user attributes
 */
public class CommandUtility {
    static void setUserRole(HttpServletRequest request,
                            UserRole role, String name) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("username", name);
        session.setAttribute("role", role);
    }
}
