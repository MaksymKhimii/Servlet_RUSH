package controller.pages;

import controller.command.Command;
import db.BasketDAO;
import db.ProductsDao;
import db.ReceipsDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class create_Receipt implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        HttpSession session = request.getSession();
        String cashier_id = (String)session.getAttribute("username");
        ReceipsDAO.addReceipt(cashier_id);

        request.setAttribute("rec", ReceipsDAO.getLastReceiptId()); //отображение id чека
       // request.setAttribute("basket", BasketDAO.getAllBasket());
        request.setAttribute("products", ProductsDao.getAllProducts());
        return "/WEB-INF/user-basic/createReceipt.jsp";
    }
}