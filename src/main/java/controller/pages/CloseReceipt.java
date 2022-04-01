package controller.pages;

import controller.command.Command;
import db.BasketDAO;
import db.ProductsDao;
import db.ReceipsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CloseReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        //TODO создать новую страницу успешного закрытия чека
        // потом редирект назад на главную страницу кассира+ удалить корзину, сохранив ее
        // в новую таблицу. Поискать и доделать косяки
        request.setAttribute("rec", ReceipsDAO.getLastReceiptId()); //отображение id чека
        request.setAttribute("totalSum", ReceipsDAO.getReceiptSum(ReceipsDAO.getLastReceiptId()));
        request.setAttribute("basket", BasketDAO.getAllBasket());
        request.setAttribute("products", ProductsDao.getAllProducts());
        return "/WEB-INF/user-basic/duringReceipt.jsp";
    }
}
