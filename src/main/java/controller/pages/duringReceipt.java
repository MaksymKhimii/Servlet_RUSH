package controller.pages;

import controller.command.Command;
import db.BasketDAO;
import db.ProductsDAO;
import db.ReceipsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class duringReceipt implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {

        request.setAttribute("rec", ReceipsDAO.getLastReceiptId()); //отображение id чека
        request.setAttribute("totalSum", ReceipsDAO.getReceiptSum(ReceipsDAO.getLastReceiptId()));
        request.setAttribute("basket", BasketDAO.getAllBasket());
        request.setAttribute("products", ProductsDAO.getAllProducts());
        return "/WEB-INF/user-basic/duringReceipt.jsp";
    }
}
