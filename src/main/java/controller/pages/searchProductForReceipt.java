package controller.pages;

import controller.command.Command;
import db.BasketDAO;
import db.ProductsDao;
import db.ReceipsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class searchProductForReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        try {
            String name = request.getParameter("name");
            if(ProductsDao.validateProduct(name)){
                //если такой продукт есть, то выводим информацию о нем
                ProductsDao.getOneProduct(name);

                request.setAttribute("rec", ReceipsDAO.getLastReceiptId()); //отображение id чека
                request.setAttribute("basket", BasketDAO.getAllBasket());
                request.setAttribute("products", ProductsDao.getAllProducts());
                //TODO если в корзине еще не продуктов то перенаправить на новую страницу без отображения таблицы слева
                // иначе же перенаправить на addToReceipt как дефолтную страницу добавления, которая будет использоваться далее
                if(BasketDAO.checkBasket()){
                    answer ="/WEB-INF/user-basic/addToReceipt.jsp";
                }else{
                        //TODO тут страница еслив таблице продуктов нет в корзине
                        answer="/WEB-INF/user-basic/firstAddProduct.jsp";
                }
            } else {
                answer ="/WEB-INF/user-basic/cashier_error.jsp";
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;
    }
}
