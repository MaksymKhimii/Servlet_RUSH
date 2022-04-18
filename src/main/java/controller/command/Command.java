package controller.command;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: основной интерфейс для обработки команд
 * ENG: main interface for command processing
 */
public interface Command {
    String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException;
}
