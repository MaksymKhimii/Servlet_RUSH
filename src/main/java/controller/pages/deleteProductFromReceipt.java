package controller.pages;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class deleteProductFromReceipt implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        //TODO сделать удаление продукта из чека
        // с сообщением о результате операции и кнопке
        // возврата на страницу со всеми продуктами
        // Примечание: надо учесть тот факт что если продуктов не осталось в чеке
        // (написать метод проверки как в basket),
        // то возращать на страницу отображения чека(id, name_cashier, total_sum)
        return null;
    }
}
