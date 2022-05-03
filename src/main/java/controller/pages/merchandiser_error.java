package controller.pages;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: обработка отображения страницы с сообщением об ошибке
 * ENG: handle page display with error message
 */
public class merchandiser_error implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        return "/WEB-INF/admin-basic/merchandiser_error.jsp";
    }

    @Override
    public String toString() {
        return "merchandiser_error";
    }
}
