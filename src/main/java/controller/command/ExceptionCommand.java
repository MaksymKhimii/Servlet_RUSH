package controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * RU: класс для обработки ошибок
 * ENG: error handling class
 */
public class ExceptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        throw new RuntimeException("Generated exception");
    }
}
