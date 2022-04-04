package controller.pages;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class SeeMoreReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        //TODO сделать страницу с отображением всех
        // продуктов одного чека и возможностью их удаления, удааления всего чека
        return null;
    }
}
