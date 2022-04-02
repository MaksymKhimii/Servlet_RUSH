package controller.pages;

import controller.command.Command;
import db.BasketDAO;
import db.GoodsArchiveDAO;
import db.ProductsDao;
import db.ReceipsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CloseReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        GoodsArchiveDAO.saveBacket(ReceipsDAO.getLastReceiptId());
        request.setAttribute("rec", ReceipsDAO.getLastReceiptId()); //отображение id чека
        request.setAttribute("totalSum", ReceipsDAO.getReceiptSum(ReceipsDAO.getLastReceiptId()));
        request.setAttribute("basket", BasketDAO.getAllBasket());
        request.setAttribute("products", ProductsDao.getAllProducts());

        if(BasketDAO.checkBasket()){
            return "/WEB-INF/user-basic/errorClosedReceipt.jsp";
        } else{
            return "/WEB-INF/user-basic/successfullyClosedReceipt.jsp";
        }

    }
}
