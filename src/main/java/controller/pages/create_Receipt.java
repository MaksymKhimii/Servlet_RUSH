package controller.pages;

import controller.command.Command;
import db.ProductsDAO;
import db.ReceiptsDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class create_Receipt implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        HttpSession session = request.getSession();
        String cashier_id = (String)session.getAttribute("username");
        ReceiptsDAO.addReceipt(cashier_id);

        request.setAttribute("rec", ReceiptsDAO.getLastReceiptId()); //отображение id чека
        //отображение суммы чека
        request.setAttribute("totalSum", ReceiptsDAO.getReceiptSum(ReceiptsDAO.getLastReceiptId()));
       // request.setAttribute("basket", BasketDAO.getAllBasket());
        request.setAttribute("products", ProductsDAO.getAllProducts());
        return "/WEB-INF/user-basic/createReceipt.jsp";
    }
}
