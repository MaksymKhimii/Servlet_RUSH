package controller.pages;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class Zreport implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        //TODO сделать обработку и отображение Z-отчета
        return null;
    }
}
