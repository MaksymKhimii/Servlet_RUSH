package controller.pages;

import controller.command.Command;
import db.ReceiptsDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: обработка удаления чека из базы данных
 * ENG:  processing the removal of a receipt from the database
 */
public class DeleteReceipt implements Command {
    private static final Logger log = Logger.getLogger(DeleteReceipt.class.getName());
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer=null;
        try {
        int idreceipt= Integer.parseInt(request.getParameter("idreceipt"));

        if(ReceiptsDAO.deleteReceipt(idreceipt)){
            log.info("Receipt: "+idreceipt+" has been deleted successfully");
            answer="/WEB-INF/st_cashier-basic/successfullyDeletedReceipt.jsp"; // успешно удалено
        } else {
            log.error("Receipt: "+idreceipt+" hasn't been deleted");
            answer="/WEB-INF/st_cashier-basic/receiptSearchError.jsp";
        }
        request.setAttribute("receipts", ReceiptsDAO.getAllReceipts());
        } catch (NumberFormatException | SQLException e){
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public String toString() {
        return "deleteReceipt";
    }
}
