package controller.pages;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class report implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        //TODO сделать обработку и отображение Z-отчета
        String answer = null;
        //TODO сделать методы которые получают суммы за чеки
        // которые были созданны в тот же день. Следовательно
        // нужно получить дату за текущий день и
        // если они совпадают -> прибавить к сумме
        // в отчете+ выставить текущее время


        answer="/WEB-INF/st_cashier-basic/report.jsp";
        return answer;
    }
}
